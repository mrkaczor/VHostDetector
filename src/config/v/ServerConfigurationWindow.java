package config.v;

import config.c.ConfigurationService;
import config.m.AuthenticationMode;
import config.m.ServerConfiguration;
import core.v.MainWindow;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;

import utils.Utils;

/**
 * Okno wprowadzania i edycji konfiguracji połączenia z serwerem.
 * @author mrkaczor
 */
public class ServerConfigurationWindow extends JDialog {
  
    private ServerConfiguration _configuration;
    
    // <editor-fold defaultstate="collapsed" desc="Creating object">
    /**
     * Creates new form ServerConfigurationWindow
     */
    public ServerConfigurationWindow() {
        super(MainWindow.getInstance(), "Ustawienia połączenia", true);
        initComponents();
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Object PRIVATE methods">
    private void refreshConfiguration() {
        _configuration = ConfigurationService.getInstance().getServerConfiguration().clone();
        
        tfServerName.setText(_configuration.getName()==null?"":_configuration.getName());
        tfHostName.setText(_configuration.getHost()==null?"":_configuration.getHost());
        cbAuthenticationMode.setSelectedItem(_configuration.getAuthenticationMode());
        tfUserName.setText(_configuration.getLogin()==null?"":_configuration.getLogin());
        pfPassword.setText(_configuration.getPassword()==null?"":_configuration.getPassword());
        tfKeyPath.setText(_configuration.getKeyPath()==null?"":_configuration.getKeyPath());
    }
    
    private void updateConfiguration() {
        _configuration.setName(tfServerName.getText());
        _configuration.setHost(tfHostName.getText());
        _configuration.setLogin(tfUserName.getText());
        _configuration.setAuthenticationMode((AuthenticationMode)cbAuthenticationMode.getSelectedItem());
        _configuration.setPassword(pfPassword.getText());
        _configuration.setKeyPath(tfKeyPath.getText());
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Object PUBLIC methods">
    @Override
    public void setVisible(boolean visibility) {
        refreshConfiguration();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((int) ((dim.width - this.getSize().width) / 2), (int) (dim.height - this.getSize().height) / 2);
        super.setVisible(visibility);
    }
    // </editor-fold>

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        lServerName = new javax.swing.JLabel();
        tfServerName = new javax.swing.JTextField();
        lHostName = new javax.swing.JLabel();
        tfHostName = new javax.swing.JTextField();
        lAuthenticationMode = new javax.swing.JLabel();
        cbAuthenticationMode = new javax.swing.JComboBox();
        lUserName = new javax.swing.JLabel();
        tfUserName = new javax.swing.JTextField();
        lPassword = new javax.swing.JLabel();
        pfPassword = new javax.swing.JPasswordField();
        lKeyPath = new javax.swing.JLabel();
        tfKeyPath = new javax.swing.JTextField();
        bKeyPath = new javax.swing.JButton();
        bSave = new javax.swing.JButton();
        bCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Konfiguracja połączenia");

        lServerName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lServerName.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lServerName.setText("Nazwa serwera:");
        lServerName.setMaximumSize(new java.awt.Dimension(120, 20));
        lServerName.setMinimumSize(new java.awt.Dimension(120, 20));

        lHostName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lHostName.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lHostName.setText("Nazwa hosta:");
        lHostName.setMaximumSize(new java.awt.Dimension(120, 20));
        lHostName.setMinimumSize(new java.awt.Dimension(120, 20));

        lAuthenticationMode.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lAuthenticationMode.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lAuthenticationMode.setText("Tryb logowania:");
        lAuthenticationMode.setMaximumSize(new java.awt.Dimension(120, 20));
        lAuthenticationMode.setMinimumSize(new java.awt.Dimension(120, 20));

        cbAuthenticationMode.setModel(new DefaultComboBoxModel(AuthenticationMode.values()));
        cbAuthenticationMode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbAuthenticationModeActionPerformed(evt);
            }
        });

        lUserName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lUserName.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lUserName.setText("Nazwa użytkownika:");
        lUserName.setMaximumSize(new java.awt.Dimension(120, 20));
        lUserName.setMinimumSize(new java.awt.Dimension(120, 20));

        lPassword.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lPassword.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lPassword.setText("Hasło:");
        lPassword.setMaximumSize(new java.awt.Dimension(120, 20));
        lPassword.setMinimumSize(new java.awt.Dimension(120, 20));

        lKeyPath.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lKeyPath.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lKeyPath.setText("Klucz:");
        lKeyPath.setMaximumSize(new java.awt.Dimension(120, 20));
        lKeyPath.setMinimumSize(new java.awt.Dimension(120, 20));

        bKeyPath.setText("...");
        bKeyPath.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bKeyPathActionPerformed(evt);
            }
        });

        bSave.setText("Zapisz");
        bSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSaveActionPerformed(evt);
            }
        });

        bCancel.setText("Anuluj");
        bCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lServerName, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tfServerName))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tfUserName))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pfPassword))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lKeyPath, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tfKeyPath, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bKeyPath, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lAuthenticationMode, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lHostName, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfHostName)
                            .addComponent(cbAuthenticationMode, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(15, 15, 15))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bSave, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(bCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(89, 89, 89))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lServerName, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfServerName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lHostName, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfHostName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lAuthenticationMode, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbAuthenticationMode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfUserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pfPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lKeyPath, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfKeyPath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bKeyPath, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bSave, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbAuthenticationModeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbAuthenticationModeActionPerformed
        AuthenticationMode selectedMode = (AuthenticationMode)cbAuthenticationMode.getSelectedItem();
        tfKeyPath.setEnabled(selectedMode != null && selectedMode.equals(AuthenticationMode.PRIVATE_KEY));
    }//GEN-LAST:event_cbAuthenticationModeActionPerformed

    private void bKeyPathActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bKeyPathActionPerformed
    	File importFile = Utils.loadFile();
        if (importFile != null) {
            tfKeyPath.setText(importFile.getAbsolutePath());
        } else {

        }
    }//GEN-LAST:event_bKeyPathActionPerformed

    private void bSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSaveActionPerformed
        updateConfiguration();
        ConfigurationService.getInstance().updateServerConfiguration(_configuration);
        this.dispose();
    }//GEN-LAST:event_bSaveActionPerformed

    private void bCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_bCancelActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bCancel;
    private javax.swing.JButton bKeyPath;
    private javax.swing.JButton bSave;
    private javax.swing.JComboBox cbAuthenticationMode;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lAuthenticationMode;
    private javax.swing.JLabel lHostName;
    private javax.swing.JLabel lKeyPath;
    private javax.swing.JLabel lPassword;
    private javax.swing.JLabel lServerName;
    private javax.swing.JLabel lUserName;
    private javax.swing.JPasswordField pfPassword;
    private javax.swing.JTextField tfHostName;
    private javax.swing.JTextField tfKeyPath;
    private javax.swing.JTextField tfServerName;
    private javax.swing.JTextField tfUserName;
    // End of variables declaration//GEN-END:variables
}
