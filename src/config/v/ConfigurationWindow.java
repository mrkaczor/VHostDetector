package config.v;

import config.c.ConfigurationService;
import config.m.AuthenticationMode;
import config.m.ResourcesConfiguration;
import config.m.ServerConfiguration;
import core.v.MainWindow;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;

/**
 *
 * @author mrkaczor
 */
public class ConfigurationWindow extends javax.swing.JDialog {

    private ServerConfiguration _configuration;
    private ResourcesConfiguration _paths;
    
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
        initComponents();
    }
    
    private void refreshConfiguration() {
        _configuration = ConfigurationService.getInstance().getServerConfiguration().clone();
        _paths = ConfigurationService.getInstance().getResourcesConfiguration().clone();
        
        tfServerName.setText(_configuration.getName()==null?"":_configuration.getName());
        tfHostName.setText(_configuration.getHost()==null?"":_configuration.getHost());
        cbAuthenticationMode.setSelectedItem(_configuration.getAuthenticationMode());
        tfUserName.setText(_configuration.getLogin()==null?"":_configuration.getLogin());
        pfPassword.setText(_configuration.getPassword()==null?"":_configuration.getPassword());
        tfKeyPath.setText(_configuration.getKeyPath()==null?"":_configuration.getKeyPath());
        
        tfGeoIPPath.setText(_paths.getGeoIPPath()==null?"":_paths.getGeoIPPath());
        tfHostMapPath.setText(_paths.getHostmapPath()==null?"":_paths.getHostmapPath());
        tfResultsPath.setText(_paths.getResultsPath()==null?"":_paths.getResultsPath());
    }
    
    private void updateConfiguration() {
        _configuration.setName(tfServerName.getText());
        _configuration.setHost(tfHostName.getText());
        _configuration.setLogin(tfUserName.getText());
        _configuration.setAuthenticationMode((AuthenticationMode)cbAuthenticationMode.getSelectedItem());
        _configuration.setPassword(pfPassword.getText());
        _configuration.setKeyPath(tfKeyPath.getText());
        
        _paths.setGeoIPPath(tfGeoIPPath.getText());
        _paths.setHostmapPath(tfHostMapPath.getText());
        _paths.setResultsPath(tfResultsPath.getText());
    }
    
    @Override
    public void setVisible(boolean visibility) {
        refreshConfiguration();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((int) ((dim.width - this.getSize().width) / 2), (int) (dim.height - this.getSize().height) / 2);
        super.setVisible(visibility);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser1 = new javax.swing.JFileChooser();
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
        pToolsSettings = new javax.swing.JPanel();
        lGeoIPPath = new javax.swing.JLabel();
        tfGeoIPPath = new javax.swing.JTextField();
        tfHostMapPath = new javax.swing.JTextField();
        lHostMapPath = new javax.swing.JLabel();
        lResultsPath = new javax.swing.JLabel();
        tfResultsPath = new javax.swing.JTextField();
        lIPListPath = new javax.swing.JLabel();
        tfIPListPath = new javax.swing.JTextField();
        bIPListPath = new javax.swing.JButton();
        bSave = new javax.swing.JButton();
        bCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Ustawienia");
        setMinimumSize(new java.awt.Dimension(450, 470));

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
                        .addComponent(lUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tfUserName))
                    .addGroup(pConnectionSettingsLayout.createSequentialGroup()
                        .addComponent(lPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pfPassword))
                    .addGroup(pConnectionSettingsLayout.createSequentialGroup()
                        .addComponent(lKeyPath, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tfKeyPath, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bKeyPath, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pConnectionSettingsLayout.createSequentialGroup()
                        .addGroup(pConnectionSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lAuthenticationMode, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lHostName, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pConnectionSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfHostName)
                            .addComponent(cbAuthenticationMode, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
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

        pToolsSettings.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Konfiguracja narzędzi", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(153, 153, 153)));

        lGeoIPPath.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lGeoIPPath.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lGeoIPPath.setText("Ścieżka do GeoIP:");
        lGeoIPPath.setMaximumSize(new java.awt.Dimension(120, 20));
        lGeoIPPath.setMinimumSize(new java.awt.Dimension(120, 20));

        lHostMapPath.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lHostMapPath.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lHostMapPath.setText("Ścieżka do hostmap:");
        lHostMapPath.setMaximumSize(new java.awt.Dimension(120, 20));
        lHostMapPath.setMinimumSize(new java.awt.Dimension(120, 20));

        lResultsPath.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lResultsPath.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lResultsPath.setText("Ścieżka do wyników:");
        lResultsPath.setMaximumSize(new java.awt.Dimension(120, 20));
        lResultsPath.setMinimumSize(new java.awt.Dimension(120, 20));

        lIPListPath.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lIPListPath.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lIPListPath.setText("Baza adresów IP:");
        lIPListPath.setMaximumSize(new java.awt.Dimension(120, 20));
        lIPListPath.setMinimumSize(new java.awt.Dimension(120, 20));

        bIPListPath.setText("...");
        bIPListPath.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bIPListPathActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pToolsSettingsLayout = new javax.swing.GroupLayout(pToolsSettings);
        pToolsSettings.setLayout(pToolsSettingsLayout);
        pToolsSettingsLayout.setHorizontalGroup(
            pToolsSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pToolsSettingsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pToolsSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pToolsSettingsLayout.createSequentialGroup()
                        .addComponent(lGeoIPPath, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tfGeoIPPath))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pToolsSettingsLayout.createSequentialGroup()
                        .addComponent(lHostMapPath, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tfHostMapPath))
                    .addGroup(pToolsSettingsLayout.createSequentialGroup()
                        .addComponent(lResultsPath, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tfResultsPath))
                    .addGroup(pToolsSettingsLayout.createSequentialGroup()
                        .addComponent(lIPListPath, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tfIPListPath)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bIPListPath, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pToolsSettingsLayout.setVerticalGroup(
            pToolsSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pToolsSettingsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pToolsSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lIPListPath, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfIPListPath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bIPListPath, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pToolsSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lGeoIPPath, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfGeoIPPath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pToolsSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lHostMapPath, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfHostMapPath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pToolsSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lResultsPath, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfResultsPath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
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
                    .addComponent(pToolsSettings, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addComponent(pToolsSettings, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bSave, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSaveActionPerformed
        updateConfiguration();
        ConfigurationService.getInstance().updateServerConfiguration(_configuration);
        ConfigurationService.getInstance().updateResourcesConfiguration(_paths);
        this.dispose();
    }//GEN-LAST:event_bSaveActionPerformed

    private void bCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_bCancelActionPerformed

    private void cbAuthenticationModeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbAuthenticationModeActionPerformed
        AuthenticationMode selectedMode = (AuthenticationMode)cbAuthenticationMode.getSelectedItem();
        tfKeyPath.setEnabled(selectedMode != null && selectedMode.equals(AuthenticationMode.PRIVATE_KEY));
    }//GEN-LAST:event_cbAuthenticationModeActionPerformed

    private void bKeyPathActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bKeyPathActionPerformed
        // TODO add your handling code here:
        int returnVal = jFileChooser1.showOpenDialog(this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = jFileChooser1.getSelectedFile();
            //This is where a real application would open the file.
            tfKeyPath.setText(file.getPath());
        } else {
            
        }
    }//GEN-LAST:event_bKeyPathActionPerformed

    private void bIPListPathActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bIPListPathActionPerformed
        // TODO add your handling code here:
        int returnVal = jFileChooser1.showOpenDialog(this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = jFileChooser1.getSelectedFile();
            //This is where a real application would open the file.
            tfIPListPath.setText(file.getPath());
        } else {
            
        }
    }//GEN-LAST:event_bIPListPathActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bCancel;
    private javax.swing.JButton bIPListPath;
    private javax.swing.JButton bKeyPath;
    private javax.swing.JButton bSave;
    private javax.swing.JComboBox cbAuthenticationMode;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lAuthenticationMode;
    private javax.swing.JLabel lGeoIPPath;
    private javax.swing.JLabel lHostMapPath;
    private javax.swing.JLabel lHostName;
    private javax.swing.JLabel lIPListPath;
    private javax.swing.JLabel lKeyPath;
    private javax.swing.JLabel lPassword;
    private javax.swing.JLabel lResultsPath;
    private javax.swing.JLabel lServerName;
    private javax.swing.JLabel lUserName;
    private javax.swing.JPanel pConnectionSettings;
    private javax.swing.JPanel pToolsSettings;
    private javax.swing.JPasswordField pfPassword;
    private javax.swing.JTextField tfGeoIPPath;
    private javax.swing.JTextField tfHostMapPath;
    private javax.swing.JTextField tfHostName;
    private javax.swing.JTextField tfIPListPath;
    private javax.swing.JTextField tfKeyPath;
    private javax.swing.JTextField tfResultsPath;
    private javax.swing.JTextField tfServerName;
    private javax.swing.JTextField tfUserName;
    // End of variables declaration//GEN-END:variables
}
