package tools.v;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;

import core.v.MainWindow;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.print.attribute.standard.PDLOverrideSupported;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import research.c.ResearchService;
import research.m.ResearchState;
import server.c.Server;

import tools.c.IPGenerator;
import tools.m.IPRange;

/**
 *
 * @author mrkaczor
 */
public class IPGeneratorWindow extends javax.swing.JDialog {

	private static final int RANGES_CLEARED = 0;
	private static final int RANGES_LOADING = 1;
	private static final int RANGES_LOADED = 2;
	private static final int ADDRESSES_GENERATING = 3;
	private static final int ADDRESSES_GENERATED = 4;
	private static final int DATA_EXPORT = 5;
	
	private int _currentState;
	private int _progressCurrent;
	private int _progressCurrentGoal;
        private long _progressStep;
        private int _progressStepSize;
        private Thread _currentTask;
        private boolean _dataSaved;
	
    /**
     * Creates new form IPGeneratorWindow
     */
    public IPGeneratorWindow() {
        super(MainWindow.getInstance(), "Generator IP", true);
        _currentState = RANGES_CLEARED;
        _dataSaved = false;
        _progressStepSize = 1;
        WindowAdapter wa = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                closeGenerator();
            }
        };
        this.addWindowListener(wa);
        initComponents();
        reinitComponents();
    }

    private void reinitComponents() {
        ((javax.swing.GroupLayout)(getContentPane().getLayout())).setHonorsVisibility(false);
    }

    private void clearData() {
        IPGenerator.getInstance().clearData();
        switchState(RANGES_CLEARED);
        _dataSaved = false;
    }

    private void closeGenerator() {
        int choice = 0;
        if(!_dataSaved) {
            Object[] opt = {"Zamknij", "Anuluj"};
            String sMessage = "Zamknięcie generatora spowoduje utratę dotychczas wczytanych/wygenerowanych informacji!";
            choice = JOptionPane.showOptionDialog(null, sMessage, "Kończenie pracy generatora", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opt, null);
        }
        if(choice == 0) {
            clearData();
            this.dispose();
        }
    }

    private String formatLongNumber(long number) {
    	String num = Long.toString(number);
    	String result = "";
    	for(int i=0; i<num.length(); i++) {
    		result = num.charAt(num.length()-i-1) + result;
    		if((i+1)%3==0 && i!=num.length()-1) {
    			result = " " + result;
    		}
    	}
    	return result;
    }

    private void refreshComponents() {
    	pInitialData.removeAll();
    	pbActionProgress.setVisible(false);
    	bAction.setText("Generuj adresy");
        bAction.setEnabled(true);
    	switch(_currentState) {
            case RANGES_CLEARED:
                pInitialData.add(pInitialDataLoad);
                bAction.setEnabled(false);
                _dataSaved = false;
                break;
            case RANGES_LOADING:
                pInitialData.add(pInitialDataLoading);
                pbActionProgress.setVisible(true);
                bAction.setEnabled(false);
                break;
            case RANGES_LOADED:
                pInitialData.add(pInitialDataDetails);
                break;
            case ADDRESSES_GENERATING:
                pInitialData.add(pInitialDataLoading);
                pbActionProgress.setVisible(true);
                bAction.setText("Zatrzymaj");
                break;
            case ADDRESSES_GENERATED:
                pInitialData.add(pSummaryData);
                bAction.setText("Wyczyść");
                break;
            case DATA_EXPORT:
                pInitialData.add(pSummaryData);
                pbActionProgress.setVisible(true);
                bAction.setText("Wyczyść");
                bAction.setEnabled(false);
                break;
    	}
    	this.revalidate();
        this.repaint();
    }

    private void resetCurrentTask(int newTaskSteps) {
    	_progressStep = 0;
        _progressStepSize = 1;
    	pbActionProgress.setMaximum(newTaskSteps);
    	pbActionProgress.setValue(0);
    }

    private void resetCurrentTask(long newTaskSteps) {
    	_progressStep = 0;
        _progressStepSize = scaleTask(newTaskSteps);
    	pbActionProgress.setMaximum((int)(newTaskSteps / _progressStepSize));
    	pbActionProgress.setValue(0);
    }

    private int scaleTask(long taskSize) {
        int scale = 1;
        while(taskSize/scale > Integer.MAX_VALUE) {
            scale++;
        }
        return scale;
    }

    private void switchState(int newState) {
        _currentState = newState;
        refreshComponents();
    }

    public void moveProgress() {
        if (_progressStep < pbActionProgress.getMaximum() * _progressStepSize) {
            pbActionProgress.setValue((int)((++_progressStep)/_progressStepSize));
            if (_progressCurrentGoal > 0 && _progressCurrent < _progressCurrentGoal) {
                lLoadingStatusBottom.setText("(" + (++_progressCurrent) + "/" + _progressCurrentGoal + ")");
            } else {
                lLoadingStatusBottom.setText("(" + _progressStep + "/" + pbActionProgress.getMaximum()*_progressStepSize + ")");
            }
        } else {
            pbActionProgress.setValue(pbActionProgress.getMaximum());
        }
    }

    @Override
    public void setVisible(boolean visibility) {
    	refreshComponents();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((int) ((dim.width - this.getSize().width) / 2), (int) (dim.height - this.getSize().height) / 2);
        super.setVisible(visibility);
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

        pInitialDataLoad.setMinimumSize(new java.awt.Dimension(300, 110));

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
                .addContainerGap(80, Short.MAX_VALUE)
                .addComponent(bLoadRanges, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(80, Short.MAX_VALUE))
        );
        pInitialDataLoadLayout.setVerticalGroup(
            pInitialDataLoadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pInitialDataLoadLayout.createSequentialGroup()
                .addContainerGap(43, Short.MAX_VALUE)
                .addComponent(bLoadRanges)
                .addContainerGap(44, Short.MAX_VALUE))
        );

        pInitialDataLoading.setMinimumSize(new java.awt.Dimension(300, 110));

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
                    .addComponent(lLoadingStatusBottom, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE))
                .addContainerGap())
        );
        pInitialDataLoadingLayout.setVerticalGroup(
            pInitialDataLoadingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pInitialDataLoadingLayout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addComponent(lLoadingStatusTop, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lLoadingStatusBottom, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        pSummaryData.setMinimumSize(new java.awt.Dimension(300, 110));

        lAddresses.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lAddresses.setText("Wygenerowanych adresów:");

        lAddressesValue.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lAddressesValue.setText("125 125 458 451");

        bSaveToFile.setText("Zapisz do pliku");
        bSaveToFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSaveToFileActionPerformed(evt);
            }
        });

        bLoadToResearch.setText("Wczytaj do badań");
        bLoadToResearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bLoadToResearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pSummaryDataLayout = new javax.swing.GroupLayout(pSummaryData);
        pSummaryData.setLayout(pSummaryDataLayout);
        pSummaryDataLayout.setHorizontalGroup(
            pSummaryDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pSummaryDataLayout.createSequentialGroup()
                .addComponent(lAddresses, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(lAddressesValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(pSummaryDataLayout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addComponent(bSaveToFile, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(bLoadToResearch, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );
        pSummaryDataLayout.setVerticalGroup(
            pSummaryDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pSummaryDataLayout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addGroup(pSummaryDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lAddressesValue, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lAddresses))
                .addGap(18, 18, 18)
                .addGroup(pSummaryDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bSaveToFile)
                    .addComponent(bLoadToResearch))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(400, 250));
        setPreferredSize(new java.awt.Dimension(450, 300));

        lTitle.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lTitle.setText("Generator IP");

        pInitialData.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Dane wejściowe", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(153, 153, 153)));
        pInitialData.setMinimumSize(new java.awt.Dimension(380, 130));
        pInitialData.setPreferredSize(new java.awt.Dimension(380, 130));
        pInitialData.setLayout(new javax.swing.BoxLayout(pInitialData, javax.swing.BoxLayout.LINE_AXIS));

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
                    .addComponent(lAddressesCountValue, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
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

        pInitialData.add(pInitialDataDetails);

        bAction.setText("Generuj adresy");
        bAction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bActionActionPerformed(evt);
            }
        });

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
                        .addComponent(pbActionProgress, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                        .addComponent(pbActionProgress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        	final File[] files = fileChooser.getSelectedFiles();
        	lFilesValue.setText(""+files.length);
            
            _currentTask = new Thread(new Runnable() {
                @Override
                public void run() {
                    switchState(RANGES_LOADING);
                    lLoadingStatusTop.setText("Wczytywanie zakresów adresów IP...");
                    resetCurrentTask(files.length);
                    int ranges = IPGenerator.getInstance().loadRanges(files);
                    lRangesValue.setText("" + ranges);

//                    try {
//                        Thread.sleep(500);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }

                    lLoadingStatusTop.setText("Obliczanie puli adresów IP...");
                    resetCurrentTask(ranges);
                    long addresses = 0;
                    for (IPRange range : IPGenerator.getInstance().getRanges()) {
                        addresses += IPGenerator.getInstance().calculatePossibleAddresses(range);
                        moveProgress();
                    }
                    lAddressesCountValue.setText(formatLongNumber(addresses));
                    switchState(RANGES_LOADED);
                }
            });
            _currentTask.start();
        };
    }//GEN-LAST:event_bLoadRangesActionPerformed

    private void bActionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bActionActionPerformed
        switch(_currentState) {
            case RANGES_LOADED:
                _currentTask = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        switchState(ADDRESSES_GENERATING);
                        lLoadingStatusTop.setText("Generowanie adresów IP...");
                        resetCurrentTask(IPGenerator.getInstance().getRanges().size());
                        long addresses = IPGenerator.getInstance().rangesToIPList();
                        lAddressesValue.setText(formatLongNumber(addresses));
                        switchState(ADDRESSES_GENERATED);
                    }
                });
                _currentTask.start();
                break;
            
            case ADDRESSES_GENERATING:
                _currentTask.stop();
                switchState(ADDRESSES_GENERATED);
                break;
            
            case ADDRESSES_GENERATED:
                clearData();
                break;
        }
    }//GEN-LAST:event_bActionActionPerformed

    private void bSaveToFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSaveToFileActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setMultiSelectionEnabled(false);
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        if(fileChooser.showOpenDialog(MainWindow.getInstance()) == JFileChooser.APPROVE_OPTION) {
            if(IPGenerator.getInstance().exportAddresses(fileChooser.getSelectedFile())) {
                _dataSaved = true;
                JOptionPane.showMessageDialog(null, "Pomyślnie wyeksportowano wygenerowane dane do pliku!", "Eksport danych", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Wystąpił błąd, w wyniku którego nie udało się zapisać wygenerowanych danych do pliku!", "Eksport danych", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_bSaveToFileActionPerformed

    private void bLoadToResearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bLoadToResearchActionPerformed
        ResearchService.getInstance().loadResearchData();
        ResearchState state = ResearchService.getInstance().getResearchData().getCurrentState();
        if(state == ResearchState.NOT_STARTED) {
            
            _currentTask = new Thread(new Runnable() {
                @Override
                public void run() {
                    switchState(DATA_EXPORT);
                    lLoadingStatusTop.setText("Wczytywanie adresów IP do badań...");
                    long addresses = IPGenerator.getInstance().getAddressesCount();
                    resetCurrentTask(addresses);
                    if(IPGenerator.getInstance().transferAddressesToReaserch()) {
                        JOptionPane.showMessageDialog(null, "Pomyślnie wczytano wygenerowane dane do badań!", "Inicjalizacja badań", JOptionPane.INFORMATION_MESSAGE);
                        _dataSaved = true;
                    } else {
                        JOptionPane.showMessageDialog(null, "Nie udało się wczytać wygenerowanych danych do badań!", "Inicjalizacja badań", JOptionPane.ERROR_MESSAGE);
                    }
                    switchState(ADDRESSES_GENERATED);
                }
            });
            _currentTask.start();
        } else {
            JOptionPane.showMessageDialog(null, "Nie można wczytać danych do badań, ponieważ zostały już one uruchomione/zakończone!", "Inicjalizacja badań", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_bLoadToResearchActionPerformed

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
