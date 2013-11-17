package server.v;

import config.c.ConfigurationService;
import config.m.ServerConfiguration;
import core.v.MainWindow;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import server.c.Server;
import server.m.Console;
import server.m.LogMessage;

/**
 *
 * @author mrkaczor
 */
public class ConsoleWindow extends javax.swing.JDialog {
    
    private int _cleanIndex;
    
    /**
     * Creates new form ServerWindow
     */
    public ConsoleWindow() {
        super(MainWindow.getInstance(), false);
        _cleanIndex = 0;
        initComponents();
    }

    private void refreshComponents() {
        ServerConfiguration serverConfig = ConfigurationService.getInstance().getServerConfiguration();
        boolean connectionState = Server.getInstance().isConnected();
        
        lServerName.setText(serverConfig.getName()==null||serverConfig.getName().equals("")?"Serwer":serverConfig.getName());
        String host = "";
        if(!isEmpty(serverConfig.getHost())) {
            host += serverConfig.getHost();
            if(!isEmpty(serverConfig.getLogin())) {
                host = serverConfig.getLogin() + "@" + host;
            }
        }
        lServerHost.setText(host);
    }
    
    private String formatTime(Date time) {
        SimpleDateFormat df = new SimpleDateFormat("[HH:mm:ss]");
        return df.format(time);
    }
    
    private boolean isEmpty(String message) {
        return message==null || message.equals("");
    }

    public void refreshConsole() {
        List<LogMessage> logs = Server.getInstance().console().getLogs();
        logs = logs.subList(_cleanIndex, logs.size());
        taConsole.setText("");
        for(LogMessage log : logs) {
            if(log.getLevel() == Console.ERROR) {
                taConsole.setText(taConsole.getText()+formatTime(log.getDate())+"  [ERROR]   "+log.getMessage()+"\n");
            } else if(log.getLevel() == Console.MESSAGE) {
                taConsole.setText(taConsole.getText()+formatTime(log.getDate())+"  [INFO]    "+log.getMessage()+"\n");
            } else {
                taConsole.setText(taConsole.getText()+formatTime(log.getDate())+"  [SYSTEM]  "+log.getMessage()+"\n");
            }
        }
    }

    @Override
    public void setVisible(boolean visibility) {
        refreshComponents();
        refreshConsole();
        super.setVisible(visibility);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lServerName = new javax.swing.JLabel();
        bClose = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        taConsole = new javax.swing.JTextArea();
        lServerHost = new javax.swing.JLabel();
        bClean = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lServerName.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lServerName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lServerName.setText("Serwer");
        lServerName.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        bClose.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bClose.setText("Zamknij");
        bClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCloseActionPerformed(evt);
            }
        });

        taConsole.setEditable(false);
        taConsole.setColumns(20);
        taConsole.setRows(5);
        taConsole.setBorder(null);
        taConsole.setMinimumSize(new java.awt.Dimension(450, 380));
        jScrollPane1.setViewportView(taConsole);

        lServerHost.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lServerHost.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lServerHost.setText("username@host");
        lServerHost.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        bClean.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bClean.setText("Wyczyść");
        bClean.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCleanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 372, Short.MAX_VALUE)
                        .addComponent(bClean, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(bClose, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lServerHost, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lServerName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lServerName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lServerHost, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bClose, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bClean, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCloseActionPerformed
        dispose();
    }//GEN-LAST:event_bCloseActionPerformed

    private void bCleanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCleanActionPerformed
        _cleanIndex = Server.getInstance().console().logCount();
        taConsole.setText("");
    }//GEN-LAST:event_bCleanActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bClean;
    private javax.swing.JButton bClose;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lServerHost;
    private javax.swing.JLabel lServerName;
    private javax.swing.JTextArea taConsole;
    // End of variables declaration//GEN-END:variables
}
