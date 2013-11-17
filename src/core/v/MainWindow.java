package core.v;

import config.c.ConfigurationService;
import server.v.ConsoleWindow;
import config.v.ResourceConfigurationWindow;
import config.v.ServerConfigurationWindow;
import research.c.HostsService;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import research.c.ResearchService;
import research.m.ResearchData;
import research.m.ResearchState;
import server.c.Server;

/**
 *
 * @author mrkaczor
 */
public class MainWindow extends JFrame {
    
    // <editor-fold defaultstate="collapsed" desc="Object variables">
    private ConsoleWindow _serverDetails;
    private ResourceConfigurationWindow _resourceConfig;
    private ServerConfigurationWindow _serverConfig;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Creating object">
    // <editor-fold defaultstate="collapsed" desc="Singleton">
    public static MainWindow getInstance() {
        return MainWindowHolder.INSTANCE;
    }
    
    private static class MainWindowHolder {
        private static final MainWindow INSTANCE = new MainWindow();
    }
    // </editor-fold>
    
    /**
     * Creates new form MainWindow
     */
    private MainWindow() {
        _serverDetails = new ConsoleWindow();
        _resourceConfig = new ResourceConfigurationWindow();
        _serverConfig = new ServerConfigurationWindow();
        WindowAdapter wa = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                closeApplication();
            }
        };
        this.addWindowListener(wa);
        initComponents();
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Object PRIVATE methods">
    private String calculateResearchProgress(int total, int done) {
        String progress;
        if(total > 0) {
            progress = done + "/" + total;
            progress += "(" + (int)(done/total*100) + "%)";
        } else {
            progress = "n/a";
        }
        return progress;
    }

    private void closeApplication() {
        int val = 0;
        if(Server.getInstance().isConnected()) {
            Object[] opt = {"TAK", "NIE"};
            String sMessage = "Zakończenie aplikacji spowoduje rozłączenie z serwerem i utratę wszystkich niezapisanych informacji!\nCzy jesteś pewien, że chcesz zamknąć połączenie z serwerem i zakończyć aplikację?";
            val = JOptionPane.showOptionDialog(null, sMessage, "Kończenie pracy programu", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opt, null);
            if(val == 0) {
                Server.getInstance().disconnect();
            }
        }
        if(val == 0) {
            dispose();
            System.exit(0);
        }
    }

    private void refreshComponents() {
        //MENU
        boolean connectionState = Server.getInstance().isConnected();
        miConnection.setText(connectionState?"Rozłącz":"Połącz");
        miConnection.setEnabled(connectionState?true:ConfigurationService.getInstance().getServerConfiguration().isValid());
        mReasearch.setEnabled(connectionState);
        miServerConfiguration.setEnabled(!connectionState);
        
        ResearchState researchState;
        if(ResearchService.getInstance().getResearchData() != null) {
            researchState = ResearchService.getInstance().getResearchData().getCurrentState();
        } else {
            researchState = ResearchState.NOT_STARTED;
        }
        if(researchState != null) {
            miStartStopResearch.setText(!researchState.equals(ResearchState.STARTED)?"Rozpocznij badania":"Zakończ badania");
            miStartStopResearch.setEnabled(researchState.equals(ResearchState.NOT_STARTED) || researchState.equals(ResearchState.STARTED));
            miResearchConfiguration.setEnabled(researchState.equals(ResearchState.NOT_STARTED));
        } else {
            miStartStopResearch.setText("Rozpocznij badania");
            miStartStopResearch.setEnabled(connectionState);
            miResearchConfiguration.setEnabled(connectionState);
        }
        //END-MENU
        
        //MAIN_PANEL
        pMainPanel.removeAll();
        if(!connectionState) {
            pMainPanel.add(pMessagePanel);
            String sMessage = "Aby móc rozpocząć lub nadzorować przebieg badań, skonfiguruj a następnie nawiąż połączenie z serwerem.";
            
            taMessage.setText(sMessage);
        } else {
            pMainPanel.add(pResearchDetails);
            ResearchData research = ResearchService.getInstance().getResearchData();
            if(research != null) {
                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyy HH:mm:ss");
                lResearchStateValue.setText(researchState==null?"n/a":researchState.toString());
                lResearchStartDateValue.setText(research.getStartDate()!=null?df.format(research.getStartDate()):"n/a");
                lResearchProgressValue.setText(calculateResearchProgress(research.getServersTotal(),research.getServersCompleted()));
            } else {
                lResearchStateValue.setText("n/a");
                lResearchStartDateValue.setText("n/a");
                lResearchProgressValue.setText("n/a");
            }
        }
        this.revalidate();
        this.repaint();
        //END-MAIN_PANEL
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Object PUBLIC methods">
    @Override
    public void setVisible(boolean visibility) {
        refreshComponents();
        super.setVisible(visibility);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((int) ((dim.width - this.getSize().width) / 2), (int) (dim.height - this.getSize().height) / 2);
    }
    
    public void refreshServerConsole(boolean showConsole) {
        if(showConsole) {
            _serverDetails.setVisible(true);
        } else {
            _serverDetails.refreshConsole();
        }
    }
    // </editor-fold>

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pResearchDetails = new javax.swing.JPanel();
        lResearchState = new javax.swing.JLabel();
        lResearchStateValue = new javax.swing.JLabel();
        lResearchStartDate = new javax.swing.JLabel();
        lResearchStartDateValue = new javax.swing.JLabel();
        lResearchProgress = new javax.swing.JLabel();
        lResearchProgressValue = new javax.swing.JLabel();
        pMessagePanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taMessage = new javax.swing.JTextArea();
        lApplicationName = new javax.swing.JLabel();
        pMainPanel = new javax.swing.JPanel();
        jMenu = new javax.swing.JMenuBar();
        mFile = new javax.swing.JMenu();
        miExit = new javax.swing.JMenuItem();
        mServer = new javax.swing.JMenu();
        miConnection = new javax.swing.JMenuItem();
        mServerSeparator = new javax.swing.JPopupMenu.Separator();
        miServerConfiguration = new javax.swing.JMenuItem();
        miResourceConfiguration = new javax.swing.JMenuItem();
        miServerDetails = new javax.swing.JMenuItem();
        mReasearch = new javax.swing.JMenu();
        miStartStopResearch = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        miResearchConfiguration = new javax.swing.JMenuItem();

        pResearchDetails.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Badania"));

        lResearchState.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lResearchState.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lResearchState.setText("Status badań:");

        lResearchStateValue.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lResearchStateValue.setText("nieznany");

        lResearchStartDate.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lResearchStartDate.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lResearchStartDate.setText("Data rozpoczęcia:");

        lResearchStartDateValue.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lResearchStartDateValue.setText("-");

        lResearchProgress.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lResearchProgress.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lResearchProgress.setText("Stopień zaawansowania:");

        lResearchProgressValue.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lResearchProgressValue.setText("-");

        javax.swing.GroupLayout pResearchDetailsLayout = new javax.swing.GroupLayout(pResearchDetails);
        pResearchDetails.setLayout(pResearchDetailsLayout);
        pResearchDetailsLayout.setHorizontalGroup(
            pResearchDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pResearchDetailsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pResearchDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pResearchDetailsLayout.createSequentialGroup()
                        .addComponent(lResearchState, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lResearchStateValue, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))
                    .addGroup(pResearchDetailsLayout.createSequentialGroup()
                        .addComponent(lResearchStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lResearchStartDateValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pResearchDetailsLayout.createSequentialGroup()
                        .addComponent(lResearchProgress, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lResearchProgressValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pResearchDetailsLayout.setVerticalGroup(
            pResearchDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pResearchDetailsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pResearchDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lResearchState, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lResearchStateValue, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pResearchDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lResearchStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lResearchStartDateValue, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pResearchDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lResearchProgress, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lResearchProgressValue, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jScrollPane1.setBorder(null);

        taMessage.setEditable(false);
        taMessage.setBackground(new java.awt.Color(240, 240, 240));
        taMessage.setColumns(20);
        taMessage.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        taMessage.setLineWrap(true);
        taMessage.setRows(5);
        taMessage.setBorder(null);
        jScrollPane1.setViewportView(taMessage);

        javax.swing.GroupLayout pMessagePanelLayout = new javax.swing.GroupLayout(pMessagePanel);
        pMessagePanel.setLayout(pMessagePanelLayout);
        pMessagePanelLayout.setHorizontalGroup(
            pMessagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
        );
        pMessagePanelLayout.setVerticalGroup(
            pMessagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("VHostDetector v1.0");
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
        });

        lApplicationName.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lApplicationName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lApplicationName.setText("VHostDetector");

        pMainPanel.setLayout(new java.awt.BorderLayout());

        mFile.setText("Plik");

        miExit.setText("Zakończ");
        miExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miExitActionPerformed(evt);
            }
        });
        mFile.add(miExit);

        jMenu.add(mFile);

        mServer.setText("Serwer");

        miConnection.setText("Połącz");
        miConnection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miConnectionActionPerformed(evt);
            }
        });
        mServer.add(miConnection);
        mServer.add(mServerSeparator);

        miServerConfiguration.setText("Konfiguruj połączenie");
        miServerConfiguration.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miServerConfigurationActionPerformed(evt);
            }
        });
        mServer.add(miServerConfiguration);

        miResourceConfiguration.setText("Konfiguruj zasoby");
        miResourceConfiguration.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miResourceConfigurationActionPerformed(evt);
            }
        });
        mServer.add(miResourceConfiguration);

        miServerDetails.setText("Wyświetl konsolę");
        miServerDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miServerDetailsActionPerformed(evt);
            }
        });
        mServer.add(miServerDetails);

        jMenu.add(mServer);

        mReasearch.setText("Badania");

        miStartStopResearch.setText("Rozpocznij badania");
        miStartStopResearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miStartStopResearchActionPerformed(evt);
            }
        });
        mReasearch.add(miStartStopResearch);
        mReasearch.add(jSeparator1);

        miResearchConfiguration.setText("Konfiguracja");
        mReasearch.add(miResearchConfiguration);

        jMenu.add(mReasearch);

        setJMenuBar(jMenu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pMainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lApplicationName, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lApplicationName, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pMainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void miServerDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miServerDetailsActionPerformed
        if(_serverDetails == null) {
            _serverDetails = new ConsoleWindow();
        }
        _serverDetails.setVisible(true);
    }//GEN-LAST:event_miServerDetailsActionPerformed

    private void miResourceConfigurationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miResourceConfigurationActionPerformed
        if(_resourceConfig == null) {
            _resourceConfig = new ResourceConfigurationWindow();
        }
        _resourceConfig.setVisible(true);
    }//GEN-LAST:event_miResourceConfigurationActionPerformed

    private void miServerConfigurationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miServerConfigurationActionPerformed
        if(_serverConfig == null) {
            _serverConfig = new ServerConfigurationWindow();
        }
        _serverConfig.setVisible(true);
    }//GEN-LAST:event_miServerConfigurationActionPerformed

    private void miConnectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miConnectionActionPerformed
        boolean operationResult;
        if(Server.getInstance().isConnected()) {
            operationResult = Server.getInstance().disconnect();
        } else {
            operationResult = Server.getInstance().connect();
            if(operationResult) {
                ResearchService.getInstance().loadResearchData();
            }
        }
        if(operationResult) {
            refreshComponents();
        }
    }//GEN-LAST:event_miConnectionActionPerformed

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        refreshComponents();
    }//GEN-LAST:event_formWindowGainedFocus

    private void miExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miExitActionPerformed
        closeApplication();
    }//GEN-LAST:event_miExitActionPerformed

    private void miStartStopResearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miStartStopResearchActionPerformed
        ResearchState state = ResearchState.NOT_STARTED;
        if(ResearchService.getInstance().getResearchData() != null) {
            state = ResearchService.getInstance().getResearchData().getCurrentState();
        }
        if(ResearchState.NOT_STARTED.equals(state)) {
            ResearchService.getInstance().startResearch();
            refreshComponents();
        }
    }//GEN-LAST:event_miStartStopResearchActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar jMenu;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JLabel lApplicationName;
    private javax.swing.JLabel lResearchProgress;
    private javax.swing.JLabel lResearchProgressValue;
    private javax.swing.JLabel lResearchStartDate;
    private javax.swing.JLabel lResearchStartDateValue;
    private javax.swing.JLabel lResearchState;
    private javax.swing.JLabel lResearchStateValue;
    private javax.swing.JMenu mFile;
    private javax.swing.JMenu mReasearch;
    private javax.swing.JMenu mServer;
    private javax.swing.JPopupMenu.Separator mServerSeparator;
    private javax.swing.JMenuItem miConnection;
    private javax.swing.JMenuItem miExit;
    private javax.swing.JMenuItem miResearchConfiguration;
    private javax.swing.JMenuItem miResourceConfiguration;
    private javax.swing.JMenuItem miServerConfiguration;
    private javax.swing.JMenuItem miServerDetails;
    private javax.swing.JMenuItem miStartStopResearch;
    private javax.swing.JPanel pMainPanel;
    private javax.swing.JPanel pMessagePanel;
    private javax.swing.JPanel pResearchDetails;
    private javax.swing.JTextArea taMessage;
    // End of variables declaration//GEN-END:variables
}
