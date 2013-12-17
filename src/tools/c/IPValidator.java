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
import server.c.Server;
import server.m.Console;
import tools.m.IPAddress;
import utils.Utils;

/**
 *
 * @author mrkaczor
 */
public class IPValidator {

    public final String BUFFER_FILE = "data";

    private int _packetsCount;
    private File _buffer;
    private File _inputAddresses;
    private long _inputAddressesCount;

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
        _packetsCount = 3;
    }
    // </editor-fold>

    private long countEntriesInFile(File file) {
        long entries = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            while(br.readLine()!=null) {
                entries++;
            }
            br.close();
        } catch(IOException ex) {
        }
        return entries;
    }

    public void clearData() {
        _inputAddresses = null;
        _inputAddressesCount = 0;
        if(_buffer != null) {
            if(!_buffer.delete()) {
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
        if(_buffer != null) {
            String line;
            int count = 0;
            Date start = new Date();
            if (!_buffer.renameTo(file)) {
                try {
                    br = new BufferedReader(new FileReader(_buffer));
                    bw = new BufferedWriter(new FileWriter(file));
                    while((line=br.readLine()) != null) {
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
            if(count > 0) {
                Server.getInstance().log(Console.SYSTEM, "Pomyślnie wyeksportowano " + count + " adresów IP w czasie " + Utils.formatTime(time) + "!", false);
            } else {
                Server.getInstance().log(Console.SYSTEM, "Pomyślnie wyeksportowano dane do pliku w czasie "+Utils.formatTime(time)+"!", false);
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
        Process p;
        float ratio = 0;
        //System.out.println("Validating address: " + address);
        try {
            p = Runtime.getRuntime().exec("ping -n " + _packetsCount + " " + address);
            p.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line;
            while ((line=reader.readLine()) != null) {
                if(line.contains("Packets")) {
                    break;
                }
            }
            int anchor = line.indexOf(",");
            int sent = Integer.parseInt(line.substring(line.indexOf("=")+2, anchor));
            line = line.substring(anchor+13);
            int recieved = Integer.parseInt(line.substring(0, line.indexOf(",")));
            ratio = 100f*recieved/sent;
            System.out.println("["+address+"] [sent="+sent+"] [recieved="+recieved+"] [ratio="+ratio+"]");
        } catch(IOException | InterruptedException ex) {
            
        }
        return ratio > 0;
    }

    public long validateIPs() {
        int valid = 0;
        BufferedReader br;
        BufferedWriter bw;
        try {
            _buffer = new File(BUFFER_FILE);
            br = new BufferedReader(new FileReader(_inputAddresses));
            bw = new BufferedWriter(new FileWriter(_buffer));
            String address;
            while((address=br.readLine())!=null) {
                if(validateIP(address)) {
                    bw.write(address+"\n");
                    valid++;
                }
                MainWindow.getInstance().getIPValidator().moveProgress();
            }
            br.close();
            bw.close();
        } catch (IOException ex) {
            Server.getInstance().log(Console.SYSTEM, "Wystąpił błąd bufora podczas weryfikowania adresów IP!", true);
        }
        return valid;
    }

}
