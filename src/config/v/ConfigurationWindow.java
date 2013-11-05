package config.v;

import config.c.ConfigurationService;
import config.m.AuthenticationMode;
import config.m.ServerConfiguration;
import core.v.MainWindow;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author mrkaczor
 */
public class ConfigurationWindow extends javax.swing.JDialog {

    private ServerConfiguration _configuration;
    
    public static ConfigurationWindow getInstance() {
        return ConfigurationWindowHolder.INSTANCE;
    }
    
    private static class ConfigurationWindowHolder {
        private static final ConfigurationWindow INSTANCE = new ConfigurationWindow();
    }
    
    /**
     * Creates new form ConfigurationWindow
     */
    private ConfigurationWindow() {
        super(MainWindow.getInstance(), "Ustawienia", true);
        _configuration = ConfigurationService.getInstance().getServerConfiguration().clone();
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        pConnectionSettings = new javax.swing.JPanel();
        lServerName = new javax.swing.JLabel();
        lHostName = new javax.swing.JLabel();
        lAuthenticationMode = new javax.swing.JLabel();
        tfServerName = new javax.swing.JTextField();
        tfHostName = new javax.swing.JTextField();
        cbAuthenticationMode = new javax.swing.JComboBox();
        lUserName = new javax.swing.JLabel();
        tfUserName = new javax.swing.JTextField();
        lPassword = new javax.swing.JLabel();
        pfPassword = new javax.swing.JPasswordField();
        lKeyPath = new javax.swing.JLabel();
        tfKeyPath = new javax.swing.JTextField();
        bKeyPath = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        bSave = new javax.swing.JButton();
        bCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Ustawienia");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Ustawienia");

        pConnectionSettings.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Konfiguracja połączenia", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(153, 153, 153)));

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

        tfServerName.setText(_configuration.getName()==null?"":_configuration.getName());

        tfHostName.setText(_configuration.getHost()==null?"":_configuration.getHost());

        cbAuthenticationMode.setModel(new DefaultComboBoxModel(AuthenticationMode.values()));
        cbAuthenticationMode.setSelectedItem(_configuration.getAuthenticationMode());

        lUserName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lUserName.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lUserName.setText("Nazwa użytkownika:");
        lUserName.setMaximumSize(new java.awt.Dimension(120, 20));
        lUserName.setMinimumSize(new java.awt.Dimension(120, 20));

        tfUserName.setText(_configuration.getLogin()==null?"":_configuration.getLogin());

        lPassword.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lPassword.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lPassword.setText("Hasło:");
        lPassword.setMaximumSize(new java.awt.Dimension(120, 20));
        lPassword.setMinimumSize(new java.awt.Dimension(120, 20));

        pfPassword.setText(_configuration.getPassword()==null?"":_configuration.getPassword());

        lKeyPath.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lKeyPath.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lKeyPath.setText("Klucz:");
        lKeyPath.setMaximumSize(new java.awt.Dimension(120, 20));
        lKeyPath.setMinimumSize(new java.awt.Dimension(120, 20));

        bKeyPath.setText("...");

        javax.swing.GroupLayout pConnectionSettingsLayout = new javax.swing.GroupLayout(pConnectionSettings);
        pConnectionSettings.setLayout(pConnectionSettingsLayout);
        pConnectionSettingsLayout.setHorizontalGroup(
            pConnectionSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pConnectionSettingsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pConnectionSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pConnectionSettingsLayout.createSequentialGroup()
                        .addComponent(lServerName, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tfServerName))
                    .addGroup(pConnectionSettingsLayout.createSequentialGroup()
                        .addComponent(lAuthenticationMode, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbAuthenticationMode, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pConnectionSettingsLayout.createSequentialGroup()
                        .addComponent(lHostName, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(tfHostName))
                    .addGroup(pConnectionSettingsLayout.createSequentialGroup()
                        .addComponent(lUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tfUserName))
                    .addGroup(pConnectionSettingsLayout.createSequentialGroup()
                        .addComponent(lPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pfPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE))
                    .addGroup(pConnectionSettingsLayout.createSequentialGroup()
                        .addComponent(lKeyPath, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tfKeyPath)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bKeyPath, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pConnectionSettingsLayout.setVerticalGroup(
            pConnectionSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pConnectionSettingsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pConnectionSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lServerName, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfServerName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pConnectionSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lHostName, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfHostName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pConnectionSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lAuthenticationMode, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbAuthenticationMode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pConnectionSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfUserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pConnectionSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pfPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pConnectionSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lKeyPath, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfKeyPath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bKeyPath, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Konfiguracja narzędzi", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(153, 153, 153)));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 107, Short.MAX_VALUE)
        );

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
                    .addComponent(pConnectionSettings, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(bSave, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(bCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pConnectionSettings, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bSave, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSaveActionPerformed
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
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lAuthenticationMode;
    private javax.swing.JLabel lHostName;
    private javax.swing.JLabel lKeyPath;
    private javax.swing.JLabel lPassword;
    private javax.swing.JLabel lServerName;
    private javax.swing.JLabel lUserName;
    private javax.swing.JPanel pConnectionSettings;
    private javax.swing.JPasswordField pfPassword;
    private javax.swing.JTextField tfHostName;
    private javax.swing.JTextField tfKeyPath;
    private javax.swing.JTextField tfServerName;
    private javax.swing.JTextField tfUserName;
    // End of variables declaration//GEN-END:variables
}
