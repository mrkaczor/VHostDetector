package core.v;

import config.c.ConfigurationService;
import server.v.ServerWindow;
import config.v.ResourceConfigurationWindow;
import config.v.ServerConfigurationWindow;
import hosts.c.HostsService;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import server.c.Server;

/**
 *
 * @author mrkaczor
 */
public class MainWindow extends JFrame {
    
    // <editor-fold defaultstate="collapsed" desc="Object variables">
    private ServerWindow _serverDetails;
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
        _serverDetails = new ServerWindow();
        _resourceConfig = new ResourceConfigurationWindow();
        _serverConfig = new ServerConfigurationWindow();
        initComponents();
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Object PRIVATE methods">
    private void refreshComponents() {
        boolean connectionState = Server.getInstance().isConnected();
        miConnection.setText(connectionState?"Rozłącz":"Połącz");
        miConnection.setEnabled(connectionState?true:ConfigurationService.getInstance().getServerConfiguration().isValid());
        miServerConfiguration.setEnabled(!connectionState);
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

        jLabel1 = new javax.swing.JLabel();
        bDetectHosts = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        mFile = new javax.swing.JMenu();
        miExit = new javax.swing.JMenuItem();
        mServer = new javax.swing.JMenu();
        miConnection = new javax.swing.JMenuItem();
        mServerSeparator = new javax.swing.JPopupMenu.Separator();
        miServerConfiguration = new javax.swing.JMenuItem();
        miResourceConfiguration = new javax.swing.JMenuItem();
        miServerDetails = new javax.swing.JMenuItem();
        mReasearch = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("VHostDetector v1.0");
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("VHostDetector");

        bDetectHosts.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bDetectHosts.setText("Wykryj hosty");
        bDetectHosts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bDetectHostsActionPerformed(evt);
            }
        });

        mFile.setText("Plik");

        miExit.setText("Zakończ");
        mFile.add(miExit);

        jMenuBar1.add(mFile);

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

        jMenuBar1.add(mServer);

        mReasearch.setText("Badania");
        jMenuBar1.add(mReasearch);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(bDetectHosts, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(98, 98, 98)
                .addComponent(bDetectHosts, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(245, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bDetectHostsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bDetectHostsActionPerformed
        HostsService.getInstance().detectVirtualHosts();
    }//GEN-LAST:event_bDetectHostsActionPerformed

    private void miServerDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miServerDetailsActionPerformed
        if(_serverDetails == null) {
            _serverDetails = new ServerWindow();
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
        Server.getInstance().connect();
    }//GEN-LAST:event_miConnectionActionPerformed

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        refreshComponents();
    }//GEN-LAST:event_formWindowGainedFocus

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bDetectHosts;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu mFile;
    private javax.swing.JMenu mReasearch;
    private javax.swing.JMenu mServer;
    private javax.swing.JPopupMenu.Separator mServerSeparator;
    private javax.swing.JMenuItem miConnection;
    private javax.swing.JMenuItem miExit;
    private javax.swing.JMenuItem miResourceConfiguration;
    private javax.swing.JMenuItem miServerConfiguration;
    private javax.swing.JMenuItem miServerDetails;
    // End of variables declaration//GEN-END:variables
}
