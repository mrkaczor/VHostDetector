package tools.v;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.JOptionPane;

import tools.c.IPValidator;
import utils.Utils;
import core.v.MainWindow;

/**
 *
 * @author mrkaczor
 */
public class IPValidatorWindow extends javax.swing.JDialog {

	private static final int ADDRESSES_CLEARED = 0;
	private static final int ADDRESSES_LOADING = 1;
	private static final int ADDRESSES_LOADED = 2;
	private static final int ADDRESSES_VALIDATING = 3;
	private static final int ADDRESSES_VALIDATED = 4;
	private static final int DATA_EXPORT = 5;
	
	private int _currentState;
	private long _progressGoal;
        private long _progressCurrent;
        private int _progressStepSize;
        private Thread _currentTask;
        private boolean _dataSaved;
	
    /**
     * Creates new form IPGeneratorWindow
     */
    public IPValidatorWindow() {
        super(MainWindow.getInstance(), "Generator IP", true);
        _currentState = ADDRESSES_CLEARED;
        _dataSaved = false;
        _progressStepSize = 1;
        WindowAdapter wa = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                closeValidator();
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
        IPValidator.getInstance().clearData();
        switchState(ADDRESSES_CLEARED);
        _dataSaved = false;
    }

    private void closeValidator() {
        if(_currentState == ADDRESSES_LOADING || _currentState == ADDRESSES_VALIDATING || _currentState == DATA_EXPORT) {
            JOptionPane.showMessageDialog(null, "Nie można zakończyć pracy walidatora ponieważ wykonuje on aktualnie operacje na danych!\n"
                    + "Zakończ aktywne operacje aby wyłączyć walidator.", "Kończenie pracy walidatora", JOptionPane.ERROR_MESSAGE);
        } else {
            int choice = 0;
            if(!_dataSaved && _currentState == ADDRESSES_VALIDATED) {
                Object[] opt = {"Zamknij", "Anuluj"};
                String sMessage = "Zamknięcie walidatora spowoduje utratę dotychczas wczytanych/wygenerowanych informacji!";
                choice = JOptionPane.showOptionDialog(null, sMessage, "Kończenie pracy walidatora", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opt, null);
            }
            if(choice == 0) {
                clearData();
                this.dispose();
            }
        }
    }

    private void refreshComponents() {
    	pInitialData.removeAll();
    	pbActionProgress.setVisible(false);
    	bAction.setText("Weryfikuj adresy");
        bAction.setEnabled(true);
    	switch(_currentState) {
            case ADDRESSES_CLEARED:
                pInitialData.add(pInitialDataLoad);
                bAction.setEnabled(false);
                _dataSaved = false;
                break;
            case ADDRESSES_LOADING:
                pInitialData.add(pTaskExecution);
                pbActionProgress.setVisible(true);
                bAction.setEnabled(false);
                break;
            case ADDRESSES_LOADED:
                pInitialData.add(pInitialDataDetails);
                break;
            case ADDRESSES_VALIDATING:
                pInitialData.add(pTaskExecution);
                pbActionProgress.setVisible(true);
                bAction.setText("Zatrzymaj");
                break;
            case ADDRESSES_VALIDATED:
                pInitialData.add(pSummaryData);
                bAction.setText("Wyczyść");
                break;
            case DATA_EXPORT:
                pInitialData.add(pTaskExecution);
                pbActionProgress.setVisible(true);
                bAction.setText("Zatrzymaj");
                bAction.setEnabled(false);
                break;
    	}
    	this.revalidate();
        this.repaint();
    }

    private void resetCurrentTask(int newTaskSteps) {
    	if(newTaskSteps > 0) {
            pbActionProgress.setIndeterminate(false);
            _progressCurrent = 0;
            _progressGoal = newTaskSteps;
            _progressStepSize = 1;
            pbActionProgress.setMaximum((int)_progressGoal);
            pbActionProgress.setValue(0);
        } else {
            pbActionProgress.setIndeterminate(true);
        }
        lLoadingStatusBottom.setText("(inicjalizacja)");
    }

    private void resetCurrentTask(long newTaskSteps) {
        if(newTaskSteps > 0) {
            pbActionProgress.setIndeterminate(false);
            _progressCurrent = 0;
            _progressGoal = newTaskSteps;
            _progressStepSize = scaleTask(newTaskSteps);
            pbActionProgress.setMaximum((int)(_progressGoal / _progressStepSize));
            pbActionProgress.setValue(0);
        } else {
            pbActionProgress.setIndeterminate(true);
        }
        lLoadingStatusBottom.setText("(inicjalizacja)");
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
        if (_progressCurrent < _progressGoal && _progressCurrent%_progressStepSize == 0) {
            pbActionProgress.setValue(pbActionProgress.getValue()+1);
            lLoadingStatusBottom.setText("(" + ++_progressCurrent + "/" + _progressGoal + ")");
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

        pTaskExecution = new javax.swing.JPanel();
        lLoadingStatusTop = new javax.swing.JLabel();
        lLoadingStatusBottom = new javax.swing.JLabel();
        pSummaryData = new javax.swing.JPanel();
        lAddressesTotal = new javax.swing.JLabel();
        lAddressesTotalValue = new javax.swing.JLabel();
        bSaveToFile = new javax.swing.JButton();
        bInitializeResearch = new javax.swing.JButton();
        lAddressesValid = new javax.swing.JLabel();
        lAddressesValidValue = new javax.swing.JLabel();
        pInitialDataLoad = new javax.swing.JPanel();
        bLoadAddresses = new javax.swing.JButton();
        lTitle = new javax.swing.JLabel();
        pInitialData = new javax.swing.JPanel();
        pInitialDataDetails = new javax.swing.JPanel();
        lAddressesLoaded = new javax.swing.JLabel();
        lAddressesLoadedValue = new javax.swing.JLabel();
        bAction = new javax.swing.JButton();
        pbActionProgress = new javax.swing.JProgressBar();

        pTaskExecution.setMinimumSize(new java.awt.Dimension(300, 110));

        lLoadingStatusTop.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lLoadingStatusTop.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        lLoadingStatusBottom.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lLoadingStatusBottom.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout pTaskExecutionLayout = new javax.swing.GroupLayout(pTaskExecution);
        pTaskExecution.setLayout(pTaskExecutionLayout);
        pTaskExecutionLayout.setHorizontalGroup(
            pTaskExecutionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pTaskExecutionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pTaskExecutionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lLoadingStatusTop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lLoadingStatusBottom, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE))
                .addContainerGap())
        );
        pTaskExecutionLayout.setVerticalGroup(
            pTaskExecutionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pTaskExecutionLayout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addComponent(lLoadingStatusTop, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lLoadingStatusBottom, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        pSummaryData.setMinimumSize(new java.awt.Dimension(300, 110));

        lAddressesTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lAddressesTotal.setText("Przeanalizowanych adresów:");

        lAddressesTotalValue.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lAddressesTotalValue.setText("125 125 458 451");

        bSaveToFile.setText("Zapisz do pliku");
        bSaveToFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSaveToFileActionPerformed(evt);
            }
        });

        bInitializeResearch.setText("Wczytaj do badań");
        bInitializeResearch.setActionCommand("Zwaliduj dane");
        bInitializeResearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bInitializeResearchActionPerformed(evt);
            }
        });

        lAddressesValid.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lAddressesValid.setText("Poprawnych adresów:");

        lAddressesValidValue.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lAddressesValidValue.setText("125 125 458 451");

        javax.swing.GroupLayout pSummaryDataLayout = new javax.swing.GroupLayout(pSummaryData);
        pSummaryData.setLayout(pSummaryDataLayout);
        pSummaryDataLayout.setHorizontalGroup(
            pSummaryDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pSummaryDataLayout.createSequentialGroup()
                .addGroup(pSummaryDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pSummaryDataLayout.createSequentialGroup()
                        .addContainerGap(32, Short.MAX_VALUE)
                        .addComponent(bSaveToFile, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(bInitializeResearch, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 17, Short.MAX_VALUE))
                    .addGroup(pSummaryDataLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pSummaryDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lAddressesValid, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lAddressesTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(pSummaryDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lAddressesTotalValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lAddressesValidValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(7, 7, 7)))
                .addGap(3, 3, 3))
        );
        pSummaryDataLayout.setVerticalGroup(
            pSummaryDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pSummaryDataLayout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addGroup(pSummaryDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lAddressesTotalValue, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lAddressesTotal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pSummaryDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lAddressesValidValue, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lAddressesValid))
                .addGap(9, 9, 9)
                .addGroup(pSummaryDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bSaveToFile)
                    .addComponent(bInitializeResearch))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pInitialDataLoad.setMinimumSize(new java.awt.Dimension(300, 110));

        bLoadAddresses.setText("Wczytaj adresy");
        bLoadAddresses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bLoadAddressesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pInitialDataLoadLayout = new javax.swing.GroupLayout(pInitialDataLoad);
        pInitialDataLoad.setLayout(pInitialDataLoadLayout);
        pInitialDataLoadLayout.setHorizontalGroup(
            pInitialDataLoadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pInitialDataLoadLayout.createSequentialGroup()
                .addContainerGap(80, Short.MAX_VALUE)
                .addComponent(bLoadAddresses, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(80, Short.MAX_VALUE))
        );
        pInitialDataLoadLayout.setVerticalGroup(
            pInitialDataLoadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pInitialDataLoadLayout.createSequentialGroup()
                .addContainerGap(43, Short.MAX_VALUE)
                .addComponent(bLoadAddresses)
                .addContainerGap(44, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(400, 250));

        lTitle.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lTitle.setText("Walidator IP");

        pInitialData.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        pInitialData.setMinimumSize(new java.awt.Dimension(380, 130));
        pInitialData.setPreferredSize(new java.awt.Dimension(380, 130));
        pInitialData.setLayout(new javax.swing.BoxLayout(pInitialData, javax.swing.BoxLayout.LINE_AXIS));

        lAddressesLoaded.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lAddressesLoaded.setText("Wczytane adresy:");

        lAddressesLoadedValue.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lAddressesLoadedValue.setText("0");

        javax.swing.GroupLayout pInitialDataDetailsLayout = new javax.swing.GroupLayout(pInitialDataDetails);
        pInitialDataDetails.setLayout(pInitialDataDetailsLayout);
        pInitialDataDetailsLayout.setHorizontalGroup(
            pInitialDataDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pInitialDataDetailsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lAddressesLoaded, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lAddressesLoadedValue, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                .addContainerGap())
        );
        pInitialDataDetailsLayout.setVerticalGroup(
            pInitialDataDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pInitialDataDetailsLayout.createSequentialGroup()
                .addContainerGap(51, Short.MAX_VALUE)
                .addGroup(pInitialDataDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lAddressesLoadedValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lAddressesLoaded, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(52, Short.MAX_VALUE))
        );

        pInitialData.add(pInitialDataDetails);

        bAction.setText("Weryfikuj adresy");
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
                        .addComponent(bAction, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bActionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bActionActionPerformed
        switch(_currentState) {
            case ADDRESSES_LOADED:
                _currentTask = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        switchState(ADDRESSES_VALIDATING);
                        lLoadingStatusTop.setText("Weryfikowanie adresów IP...");
                        long addressesTotal = IPValidator.getInstance().getLoadedInitialDataCount();
                        resetCurrentTask(addressesTotal);
                        long addressesValid = IPValidator.getInstance().validateIPs();
                        lAddressesTotalValue.setText(Utils.formatLongNumber(addressesTotal));
                        lAddressesValidValue.setText(Utils.formatLongNumber(addressesValid));
                        switchState(ADDRESSES_VALIDATED);
                    }
                });
                _currentTask.start();
                break;
            
            case ADDRESSES_VALIDATING:
                _currentTask.stop();
                lAddressesTotalValue.setText(Utils.formatLongNumber(IPValidator.getInstance().getLoadedInitialDataCount()));
                lAddressesValidValue.setText("n/a");
                //lAddressesValue.setText(Utils.formatLongNumber(IPValidator.getInstance()));
                switchState(ADDRESSES_VALIDATED);
                break;
            
            case ADDRESSES_VALIDATED:
                clearData();
                break;
        }
    }//GEN-LAST:event_bActionActionPerformed

    private void bSaveToFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSaveToFileActionPerformed
        final File exportFile = Utils.loadFile();
        if(exportFile != null) {
            _currentTask = new Thread(new Runnable() {
                @Override
                public void run() {
                    switchState(DATA_EXPORT);
                    lLoadingStatusTop.setText("Eksport adresów IP do pliku...");
                    long addresses = IPValidator.getInstance().getLoadedInitialDataCount();
                    resetCurrentTask(addresses);
                    if(IPValidator.getInstance().exportAddresses(exportFile)) {
                        _dataSaved = true;
                        JOptionPane.showMessageDialog(null, "Pomyślnie wyeksportowano wygenerowane dane do pliku!", "Eksport danych", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Wystąpił błąd, w wyniku którego nie udało się zapisać wygenerowanych danych do pliku!", "Eksport danych", JOptionPane.ERROR_MESSAGE);
                    }
                    switchState(ADDRESSES_VALIDATED);
                }
            });
            _currentTask.start();
        }
    }//GEN-LAST:event_bSaveToFileActionPerformed

    private void bInitializeResearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bInitializeResearchActionPerformed
        _currentTask = new Thread(new Runnable() {
            @Override
            public void run() {
                switchState(ADDRESSES_VALIDATING);
                lLoadingStatusTop.setText("Eksport adresów IP do pliku...");
                long addresses = IPValidator.getInstance().validateIPs();
            }
        });
        _currentTask.start();
    }//GEN-LAST:event_bInitializeResearchActionPerformed

    private void bLoadAddressesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bLoadAddressesActionPerformed
    	final File importFile = Utils.loadFile();
        if(importFile != null) {
        	_currentTask = new Thread(new Runnable() {
                @Override
                public void run() {
                    switchState(ADDRESSES_LOADING);
                    lLoadingStatusTop.setText("Wczytywanie adresów IP...");
                    resetCurrentTask(-1);
                    long addresses = IPValidator.getInstance().loadInitialAddresses(importFile);
                    lAddressesLoadedValue.setText(Utils.formatLongNumber(addresses));
                    switchState(ADDRESSES_LOADED);
                }
            });
            _currentTask.start();
        };
    }//GEN-LAST:event_bLoadAddressesActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bAction;
    private javax.swing.JButton bInitializeResearch;
    private javax.swing.JButton bLoadAddresses;
    private javax.swing.JButton bSaveToFile;
    private javax.swing.JLabel lAddressesLoaded;
    private javax.swing.JLabel lAddressesLoadedValue;
    private javax.swing.JLabel lAddressesTotal;
    private javax.swing.JLabel lAddressesTotalValue;
    private javax.swing.JLabel lAddressesValid;
    private javax.swing.JLabel lAddressesValidValue;
    private javax.swing.JLabel lLoadingStatusBottom;
    private javax.swing.JLabel lLoadingStatusTop;
    private javax.swing.JLabel lTitle;
    private javax.swing.JPanel pInitialData;
    private javax.swing.JPanel pInitialDataDetails;
    private javax.swing.JPanel pInitialDataLoad;
    private javax.swing.JPanel pSummaryData;
    private javax.swing.JPanel pTaskExecution;
    private javax.swing.JProgressBar pbActionProgress;
    // End of variables declaration//GEN-END:variables
}
