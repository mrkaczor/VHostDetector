package tools.c;

import core.v.MainWindow;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.c.Server;
import server.m.Console;
import tools.m.IPAddress;
import utils.Utils;

/**
 *
 * @author mrkaczor
 */
public class IPValidator {

    public final String BUFFER_FILE = Utils.generateTimestamp() + ".tmp";

    private int _packetsCount;
    private int _threadsCount;
    private File _buffer;
    private File _inputAddresses;
    private long _inputAddressesCount;
    private int _foundIps;
    private int _neededIps;

    // <editor-fold defaultstate="collapsed" desc="Creating object">
    // <editor-fold defaultstate="collapsed" desc="Singleton">
    public static IPValidator getInstance() {
        return IPValidatorHolder.INSTANCE;
    }

    private static class IPValidatorHolder {

        private static final IPValidator INSTANCE = new IPValidator();
    }
    // </editor-fold>

    private IPValidator() {
        _packetsCount = 1;
        _threadsCount = 100;
        _neededIps = 100;
    }
    // </editor-fold>

    private long countEntriesInFile(File file) {
        long entries = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            while (br.readLine() != null) {
                entries++;
            }
            br.close();
        } catch (IOException ex) {
        }
        return entries;
    }

    public void clearData() {
        _inputAddresses = null;
        _inputAddressesCount = 0;
        if (_buffer != null) {
            if (!_buffer.delete()) {
                try {
                    BufferedWriter bw = new BufferedWriter(new FileWriter(_buffer));
                    bw.write("Please remove this file manually!");
                    bw.close();
                    _buffer.delete();
                } catch (IOException ex) {
                    Server.getInstance().log(Console.ERROR, "Failed to delete temporary files used by IP Validator! Please try to delete it manually from directory '"
                            + _buffer.getAbsolutePath() + "' to avoid losing memory space!", true);
                }
            }
            _buffer = null;
        }
    }

    public boolean exportAddresses(File file) {
        BufferedReader br;
        BufferedWriter bw;
        long time = 0;
        if (_buffer != null) {
            String line;
            int count = 0;
            Date start = new Date();
            if (!_buffer.renameTo(file)) {
                try {
                    br = new BufferedReader(new FileReader(_buffer));
                    bw = new BufferedWriter(new FileWriter(file));
                    while ((line = br.readLine()) != null) {
                        bw.write(line + "\n");
                        count++;
                        MainWindow.getInstance().getIPValidator().moveProgress();
                    }
                    bw.close();
                } catch (IOException ex) {
                    Server.getInstance().log(Console.SYSTEM, "Nie udało się wyeksportować danych adresów IP do pliku z powodu błędu:\n" + ex.getMessage(), false);
                    return false;
                }
            }
            time = new Date().getTime() - start.getTime();
            if (count > 0) {
                Server.getInstance().log(Console.SYSTEM, "Pomyślnie wyeksportowano " + count + " adresów IP w czasie " + Utils.formatTime(time) + "!", false);
            } else {
                Server.getInstance().log(Console.SYSTEM, "Pomyślnie wyeksportowano dane do pliku w czasie " + Utils.formatTime(time) + "!", false);
            }
        }
        System.out.println("Exported addresses in:\t" + Utils.formatTime(time));
        return true;
    }

    public long getLoadedInitialDataCount() {
        return _inputAddressesCount;
    }

    public long loadInitialAddresses(File initialData) {
        _inputAddresses = initialData;
        _inputAddressesCount = countEntriesInFile(_inputAddresses);
        return _inputAddressesCount;
    }

    public boolean validateIP(IPAddress address) {
        return validateIP(address.toString());
    }

    public boolean validateIP(String address) {
        //System.out.println("Validating address: " + address);
        float ratio = 0;
        Process p;
        try {

            p = Runtime.getRuntime().exec("ping -n " + _packetsCount + " " + address);
            p.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("= " + _packetsCount + ", ")) {
                    break;
                }
            }
            int anchor = line.indexOf(",");
            int sent = Integer.parseInt(line.substring(line.indexOf("=") + 2, anchor));
            line = line.substring(anchor + 13);
            int recieved = Integer.parseInt(line.substring(0, line.indexOf(",")));
            ratio = 100f * recieved / sent;
            System.out.println("[" + address + "] [sent=" + sent + "] [recieved=" + recieved + "] [ratio=" + ratio + "]");
        } catch (IOException | InterruptedException ex) {

        }
        return ratio > 0;
    }

    private synchronized void writeIP(BufferedWriter bw, String ip, CountDownLatch latch) throws IOException {
        bw.write(ip);
        bw.newLine();
        ++_foundIps;
    }
    
    private synchronized int getFoundCount(){
        return _foundIps;
    }

    public long validateIPs() {
        _foundIps = 0;
        try {
            _buffer = new File(BUFFER_FILE);
            final BufferedReader br = new BufferedReader(new FileReader(_inputAddresses));
            final BufferedWriter bw = new BufferedWriter(new FileWriter(_buffer));
            final int ipsPerThread = (int) (_inputAddressesCount / _threadsCount);
            final int restIps = (int) (_inputAddressesCount - ipsPerThread * _threadsCount);
            final CountDownLatch latch = new CountDownLatch(_threadsCount);

            for (int i = 0; i < _threadsCount; ++i) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int j = 0; j < ipsPerThread; ++j) {
                            if(getFoundCount() > _neededIps){
                                break;
                            }
                            try {
                                String address = br.readLine();
                                if (address != null && validateIP(address)) {
                                    writeIP(bw, address, latch);
                                }
                                    MainWindow.getInstance().getIPValidator().moveProgress();
                            } catch (IOException ex) {
                                Logger.getLogger(IPValidator.class.getName()).log(Level.SEVERE, null, ex);
                            } finally {
                            }
                        }
                        latch.countDown();
                    }
                }).start();
            }
            try {
                latch.await();
            } catch (InterruptedException ex) {
                Logger.getLogger(IPValidator.class.getName()).log(Level.SEVERE, null, ex);
            }
            br.close();
            bw.close();
        } catch (IOException ex) {
            Server.getInstance().log(Console.SYSTEM, "Wystąpił błąd bufora podczas weryfikowania adresów IP!", true);
        }
        int found = _foundIps;
        _foundIps = 0;
        return found;
    }

}
