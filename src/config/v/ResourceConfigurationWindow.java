package config.v;

import config.c.ConfigurationService;
import config.m.ResourcesConfiguration;
import core.v.MainWindow;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import javax.swing.JDialog;
import javax.swing.JFileChooser;

/**
 *
 * @author mrkaczor
 */
public class ResourceConfigurationWindow extends JDialog {

    private ResourcesConfiguration _paths;
    
    /**
     * Creates new form ResourceConfigurationWindow
     */
    public ResourceConfigurationWindow() {
        super(MainWindow.getInstance(), "Ustawienia", true);
        initComponents();
    }
    
    private void refreshConfiguration() {
        _paths = ConfigurationService.getInstance().getResourcesConfiguration().clone();
        
        tfIPListPath.setText(_paths.getHostsListFilePath()==null?"":_paths.getHostsListFilePath());
        tfGeoIPPath.setText(_paths.getGeoIPPath()==null?"":_paths.getGeoIPPath());
        tfHostMapPath.setText(_paths.getHostmapPath()==null?"":_paths.getHostmapPath());
        tfResultsPath.setText(_paths.getResearchPath()==null?"":_paths.getResearchPath());
    }
    
    private void updateConfiguration() {
        _paths.setHostsListFilePath(tfIPListPath.getText());
        _paths.setGeoIPPath(tfGeoIPPath.getText());
        _paths.setHostmapPath(tfHostMapPath.getText());
        _paths.setResearchPath(tfResultsPath.getText());
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

        fileChooser = new javax.swing.JFileChooser();
        jLabel1 = new javax.swing.JLabel();
        pConnectionSettings = new javax.swing.JPanel();
        lIPListPath = new javax.swing.JLabel();
        tfIPListPath = new javax.swing.JTextField();
        bIPListPath = new javax.swing.JButton();
        pToolsSettings = new javax.swing.JPanel();
        lGeoIPPath = new javax.swing.JLabel();
        tfGeoIPPath = new javax.swing.JTextField();
        lHostMapPath = new javax.swing.JLabel();
        tfHostMapPath = new javax.swing.JTextField();
        lResearchPath = new javax.swing.JLabel();
        tfResultsPath = new javax.swing.JTextField();
        bSave = new javax.swing.JButton();
        bCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Ustawienia");
        setMinimumSize(new java.awt.Dimension(450, 470));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Konfiguracja zasobów");

        pConnectionSettings.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Zasoby lokalne", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(153, 153, 153)));

        lIPListPath.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lIPListPath.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lIPListPath.setText("Baza adresów IP:");
        lIPListPath.setMaximumSize(new java.awt.Dimension(120, 20));
        lIPListPath.setMinimumSize(new java.awt.Dimension(120, 20));

        bIPListPath.setText("...");
        bIPListPath.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bIPListPathActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pConnectionSettingsLayout = new javax.swing.GroupLayout(pConnectionSettings);
        pConnectionSettings.setLayout(pConnectionSettingsLayout);
        pConnectionSettingsLayout.setHorizontalGroup(
            pConnectionSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pConnectionSettingsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pConnectionSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lIPListPath, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pConnectionSettingsLayout.createSequentialGroup()
                        .addComponent(tfIPListPath)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bIPListPath, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pConnectionSettingsLayout.setVerticalGroup(
            pConnectionSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pConnectionSettingsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lIPListPath, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pConnectionSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfIPListPath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bIPListPath, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pToolsSettings.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Zasoby zdalne", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(153, 153, 153)));

        lGeoIPPath.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lGeoIPPath.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lGeoIPPath.setText("Ścieżka do GeoIP:");
        lGeoIPPath.setMaximumSize(new java.awt.Dimension(120, 20));
        lGeoIPPath.setMinimumSize(new java.awt.Dimension(120, 20));

        lHostMapPath.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lHostMapPath.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lHostMapPath.setText("Ścieżka do HostMap:");
        lHostMapPath.setMaximumSize(new java.awt.Dimension(120, 20));
        lHostMapPath.setMinimumSize(new java.awt.Dimension(120, 20));

        lResearchPath.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lResearchPath.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lResearchPath.setText("Ścieżka do plików badań:");
        lResearchPath.setMaximumSize(new java.awt.Dimension(120, 20));
        lResearchPath.setMinimumSize(new java.awt.Dimension(120, 20));

        javax.swing.GroupLayout pToolsSettingsLayout = new javax.swing.GroupLayout(pToolsSettings);
        pToolsSettings.setLayout(pToolsSettingsLayout);
        pToolsSettingsLayout.setHorizontalGroup(
            pToolsSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pToolsSettingsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pToolsSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lGeoIPPath, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tfGeoIPPath, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                    .addComponent(lHostMapPath, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tfHostMapPath, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                    .addComponent(lResearchPath, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tfResultsPath, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE))
                .addContainerGap())
        );
        pToolsSettingsLayout.setVerticalGroup(
            pToolsSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pToolsSettingsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lGeoIPPath, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(tfGeoIPPath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lHostMapPath, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(tfHostMapPath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lResearchPath, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(tfResultsPath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pConnectionSettings, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pToolsSettings, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bSave, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSaveActionPerformed
        updateConfiguration();
        ConfigurationService.getInstance().updateResourcesConfiguration(_paths);
        this.dispose();
    }//GEN-LAST:event_bSaveActionPerformed

    private void bCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_bCancelActionPerformed

    private void bIPListPathActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bIPListPathActionPerformed
        // TODO add your handling code here:
        int returnVal = fileChooser.showOpenDialog(this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            //This is where a real application would open the file.
            tfIPListPath.setText(file.getPath());
        } else {
            
        }
    }//GEN-LAST:event_bIPListPathActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bCancel;
    private javax.swing.JButton bIPListPath;
    private javax.swing.JButton bSave;
    private javax.swing.JFileChooser fileChooser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lGeoIPPath;
    private javax.swing.JLabel lHostMapPath;
    private javax.swing.JLabel lIPListPath;
    private javax.swing.JLabel lResearchPath;
    private javax.swing.JPanel pConnectionSettings;
    private javax.swing.JPanel pToolsSettings;
    private javax.swing.JTextField tfGeoIPPath;
    private javax.swing.JTextField tfHostMapPath;
    private javax.swing.JTextField tfIPListPath;
    private javax.swing.JTextField tfResultsPath;
    // End of variables declaration//GEN-END:variables
}
