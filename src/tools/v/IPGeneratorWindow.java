package tools.v;

import core.v.MainWindow;
import javax.swing.JFileChooser;
import tools.c.IPGenerator;

/**
 *
 * @author mrkaczor
 */
public class IPGeneratorWindow extends javax.swing.JDialog {

    /**
     * Creates new form IPGeneratorWindow
     */
    public IPGeneratorWindow() {
        super(MainWindow.getInstance(), "Generator IP", true);
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pInitialDataLoad = new javax.swing.JPanel();
        bLoadRanges = new javax.swing.JButton();
        pInitialDataLoading = new javax.swing.JPanel();
        lLoadingStatusTop = new javax.swing.JLabel();
        lLoadingStatusBottom = new javax.swing.JLabel();
        pSummaryData = new javax.swing.JPanel();
        lAddresses = new javax.swing.JLabel();
        lAddressesValue = new javax.swing.JLabel();
        bSaveToFile = new javax.swing.JButton();
        bLoadToResearch = new javax.swing.JButton();
        lTitle = new javax.swing.JLabel();
        pInitialData = new javax.swing.JPanel();
        pInitialDataDetails = new javax.swing.JPanel();
        lFiles = new javax.swing.JLabel();
        lRanges = new javax.swing.JLabel();
        lAddressesCount = new javax.swing.JLabel();
        lFilesValue = new javax.swing.JLabel();
        lRangesValue = new javax.swing.JLabel();
        lAddressesCountValue = new javax.swing.JLabel();
        bAction = new javax.swing.JButton();
        pbActionProgress = new javax.swing.JProgressBar();

        bLoadRanges.setText("Wczytaj zakresy");
        bLoadRanges.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bLoadRangesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pInitialDataLoadLayout = new javax.swing.GroupLayout(pInitialDataLoad);
        pInitialDataLoad.setLayout(pInitialDataLoadLayout);
        pInitialDataLoadLayout.setHorizontalGroup(
            pInitialDataLoadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pInitialDataLoadLayout.createSequentialGroup()
                .addContainerGap(115, Short.MAX_VALUE)
                .addComponent(bLoadRanges, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(115, Short.MAX_VALUE))
        );
        pInitialDataLoadLayout.setVerticalGroup(
            pInitialDataLoadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pInitialDataLoadLayout.createSequentialGroup()
                .addContainerGap(43, Short.MAX_VALUE)
                .addComponent(bLoadRanges)
                .addContainerGap(43, Short.MAX_VALUE))
        );

        lLoadingStatusTop.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lLoadingStatusTop.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        lLoadingStatusBottom.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lLoadingStatusBottom.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout pInitialDataLoadingLayout = new javax.swing.GroupLayout(pInitialDataLoading);
        pInitialDataLoading.setLayout(pInitialDataLoadingLayout);
        pInitialDataLoadingLayout.setHorizontalGroup(
            pInitialDataLoadingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pInitialDataLoadingLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pInitialDataLoadingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lLoadingStatusTop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lLoadingStatusBottom, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE))
                .addContainerGap())
        );
        pInitialDataLoadingLayout.setVerticalGroup(
            pInitialDataLoadingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pInitialDataLoadingLayout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addComponent(lLoadingStatusTop, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lLoadingStatusBottom, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        lAddresses.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lAddresses.setText("Wygenerowanych adresów:");

        lAddressesValue.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lAddressesValue.setText("0");

        bSaveToFile.setText("Zapisz do pliku");

        bLoadToResearch.setText("Wczytaj do badań");

        javax.swing.GroupLayout pSummaryDataLayout = new javax.swing.GroupLayout(pSummaryData);
        pSummaryData.setLayout(pSummaryDataLayout);
        pSummaryDataLayout.setHorizontalGroup(
            pSummaryDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pSummaryDataLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pSummaryDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pSummaryDataLayout.createSequentialGroup()
                        .addComponent(lAddresses, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lAddressesValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pSummaryDataLayout.createSequentialGroup()
                        .addGap(0, 36, Short.MAX_VALUE)
                        .addComponent(bSaveToFile, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(bLoadToResearch, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 36, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pSummaryDataLayout.setVerticalGroup(
            pSummaryDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pSummaryDataLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pSummaryDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lAddressesValue, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addComponent(lAddresses, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(pSummaryDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bSaveToFile)
                    .addComponent(bLoadToResearch))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lTitle.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lTitle.setText("Generator IP");

        pInitialData.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Dane wejściowe", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(153, 153, 153)));

        lFiles.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lFiles.setText("Wczytanych plików:");

        lRanges.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lRanges.setText("Wczytanych zakresów:");

        lAddressesCount.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lAddressesCount.setText("Szacowana ilość adresów IP:");

        lFilesValue.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lFilesValue.setText("0");

        lRangesValue.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lRangesValue.setText("0");

        lAddressesCountValue.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lAddressesCountValue.setText("0");

        javax.swing.GroupLayout pInitialDataDetailsLayout = new javax.swing.GroupLayout(pInitialDataDetails);
        pInitialDataDetails.setLayout(pInitialDataDetailsLayout);
        pInitialDataDetailsLayout.setHorizontalGroup(
            pInitialDataDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pInitialDataDetailsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pInitialDataDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lRanges, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lFiles, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lAddressesCount, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(pInitialDataDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lAddressesCountValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lFilesValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lRangesValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pInitialDataDetailsLayout.setVerticalGroup(
            pInitialDataDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pInitialDataDetailsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pInitialDataDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lFilesValue, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addComponent(lFiles, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pInitialDataDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lRanges, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lRangesValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pInitialDataDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lAddressesCount, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addComponent(lAddressesCountValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pInitialDataLayout = new javax.swing.GroupLayout(pInitialData);
        pInitialData.setLayout(pInitialDataLayout);
        pInitialDataLayout.setHorizontalGroup(
            pInitialDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pInitialDataLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pInitialDataDetails, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pInitialDataLayout.setVerticalGroup(
            pInitialDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pInitialDataDetails, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        bAction.setText("Generuj adresy");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pInitialData, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(pbActionProgress, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(bAction, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pInitialData, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(pbActionProgress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(bAction, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bLoadRangesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bLoadRangesActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setMultiSelectionEnabled(true);
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        if(fileChooser.showOpenDialog(MainWindow.getInstance()) == JFileChooser.APPROVE_OPTION) {
            IPGenerator.getInstance().loadRanges(fileChooser.getSelectedFiles());
            pInitialData.removeAll();
            pInitialData.add(pInitialDataLoading);
            this.revalidate();
            this.repaint();
        }
    }//GEN-LAST:event_bLoadRangesActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bAction;
    private javax.swing.JButton bLoadRanges;
    private javax.swing.JButton bLoadToResearch;
    private javax.swing.JButton bSaveToFile;
    private javax.swing.JLabel lAddresses;
    private javax.swing.JLabel lAddressesCount;
    private javax.swing.JLabel lAddressesCountValue;
    private javax.swing.JLabel lAddressesValue;
    private javax.swing.JLabel lFiles;
    private javax.swing.JLabel lFilesValue;
    private javax.swing.JLabel lLoadingStatusBottom;
    private javax.swing.JLabel lLoadingStatusTop;
    private javax.swing.JLabel lRanges;
    private javax.swing.JLabel lRangesValue;
    private javax.swing.JLabel lTitle;
    private javax.swing.JPanel pInitialData;
    private javax.swing.JPanel pInitialDataDetails;
    private javax.swing.JPanel pInitialDataLoad;
    private javax.swing.JPanel pInitialDataLoading;
    private javax.swing.JPanel pSummaryData;
    private javax.swing.JProgressBar pbActionProgress;
    // End of variables declaration//GEN-END:variables
}
