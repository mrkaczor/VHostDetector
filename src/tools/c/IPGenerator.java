package tools.c;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import core.v.MainWindow;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Date;
import research.c.HostsService;
import research.c.ResearchService;
import server.c.Server;
import server.m.Console;
import tools.m.IPAddress;
import tools.m.IPRange;
import utils.Utils;

/**
 * Serwis służący do generowania adresów IP serwerów.
 *
 * @author mrkaczor
 */
public class IPGenerator {

    public final String BUFFER_FILE = "data";

    private List<IPRange> _ranges;
    private List<IPAddress> _addresses;
    File _buffer;

    // <editor-fold defaultstate="collapsed" desc="Creating object">
    // <editor-fold defaultstate="collapsed" desc="Singleton">
    public static IPGenerator getInstance() {
        return IPGeneratorHolder.INSTANCE;
    }

    private static class IPGeneratorHolder {
        private static final IPGenerator INSTANCE = new IPGenerator();
    }
    // </editor-fold>
    
    private IPGenerator() {
        _ranges = new ArrayList<>();
        _addresses = new ArrayList<>();
    }
    // </editor-fold>

    public void clearData() {
        _ranges.clear();
        _addresses.clear();
        if(_buffer != null) {
            if(!_buffer.delete()) {
                try {
                    BufferedWriter bw = new BufferedWriter(new FileWriter(_buffer));
                    bw.write("Please remove this file manually!");
                    bw.close();
                } catch (IOException ex) {
                    Server.getInstance().log(Console.ERROR, "Failed to delete temporary files used by IP Generator! Please try to delete it manually from directory '"
                        + _buffer.getAbsolutePath() + "' to avoid losing memory space!", true);
                }
            }
            _buffer = null;
        }
    }

    public long getAddressesCount() {
        if(_buffer == null) {
            return _addresses.size();
        } else {
            try {
                BufferedReader br = new BufferedReader(new FileReader(_buffer));
                int addresses = 0;
                while(br.readLine()!=null) {
                    addresses++;
                }
                return addresses;
            } catch (IOException ex) {
                return 0;
            }
        }
    }

    public List<IPRange> getRanges() {
        return _ranges;
    }

    public void setRanges(List<IPRange> ranges) {
        _ranges = ranges;
    }

    public boolean exportAddresses(File file) {
        BufferedWriter bw;
        long time = 0;
        if(_buffer == null && _addresses.size() > 0) {
            try {
                bw = new BufferedWriter(new FileWriter(file));
                Date start = new Date();
                for(IPAddress address : _addresses) {
                    bw.write(address + "\n");
                    MainWindow.getInstance().getIPGenerator().moveProgress();
                }
                time = new Date().getTime() - start.getTime();
                Server.getInstance().log(Console.SYSTEM, "Pomyślnie wyeksportowano " + _addresses.size() + " adresów IP w czasie "+Utils.formatTime(time)+"!", false);
                bw.close();
            } catch (IOException ex) {
                Server.getInstance().log(Console.SYSTEM, "Nie udało się wyeksportować danych adresów IP do pliku z powodu błędu:\n" + ex.getMessage(), false);
                return false;
            }
        } else if(_buffer != null) {
            BufferedReader br;
            String line;
            int count = 0;
            Date start = new Date();
            if (!_buffer.renameTo(file)) {
                try {
                    br = new BufferedReader(new FileReader(_buffer));
                    bw = new BufferedWriter(new FileWriter(file));
                    while((line=br.readLine()) != null) {
                        bw.write(line);
                        count++;
                        MainWindow.getInstance().getIPGenerator().moveProgress();
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

    public int loadRanges(File[] files) {
        int rangesCount = 0;
        for(File file : files) {
            rangesCount += loadRanges(file);
            MainWindow.getInstance().getIPGenerator().moveProgress();
        }
        return rangesCount;
    }

    public int loadRanges(File file) {
        int rangesCount = 0;
        String line;
        IPRange range;
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(file));
            line = br.readLine();
            while (line != null) {
                range = new IPRange(line.substring(0, line.indexOf('/')), Integer.parseInt(line.substring(line.indexOf('/') + 1, line.length())));
                _ranges.add(range);
                rangesCount++;
                line = br.readLine();
            }
            br.close();
        } catch (FileNotFoundException e) {
            Server.getInstance().log(Console.SYSTEM, "Nie można wczytać zakresów IP z pliku '" + file.getPath() + "':\n" + e.getMessage(), true);
        } catch (IOException e) {
            Server.getInstance().log(Console.SYSTEM, "Nie można wczytać zakresów IP z pliku '" + file.getPath() + "' z powodu błędu:\n" + e.getMessage(), true);
        }
        return rangesCount;
    }

    public long calculatePossibleAddresses(IPRange range) {
        int constOctets = range.getMask() / 8;
        long count = 1;
        int temp;
        for(int i=constOctets; i<IPAddress.OCTETS_COUNT; i++) {
            temp = range.getMask() - i * 8;
            count *= 1 << (temp>=0 ? 8-temp : 8);
        }
        return count;
    }

    public List<IPAddress> rangeToIPList(IPRange range) throws IllegalArgumentException {
        List<IPAddress> addresses = new ArrayList<>();

        IPAddress base = range.getBaseAddress();
        //Should be multi-threaded !!
        List<IPAddress> temp_addresses = generateIPAddressesForOctet(base, 1, (byte)range.getOctetMaxValue(1));
        for (IPAddress addr : temp_addresses) {
            List<IPAddress> temp_addresses2 = generateIPAddressesForOctet(addr, 2, (byte)range.getOctetMaxValue(2));
            for (IPAddress addr2 : temp_addresses2) {
                List<IPAddress> temp_addresses3 = generateIPAddressesForOctet(addr2, 3, (byte)range.getOctetMaxValue(3));
                for (IPAddress addr3 : temp_addresses3) {
                    List<IPAddress> final_addresses = generateIPAddressesForOctet(addr3, 4, (byte)range.getOctetMaxValue(4));
                    for (IPAddress addr4 : final_addresses) {
                        addresses.add(addr4);
                    }
                }
            }
        }

        return addresses;
    }

    public long rangesToIPList(boolean buffer) {
        long addresses = 0;
        List<IPAddress> tmpList;
        if(!_ranges.isEmpty()) {
            _addresses.clear();
            Server.getInstance().log(Console.SYSTEM, "Generowanie adresów IP z " + _ranges.size() + " zakresów...", false);
            Date start = new Date();
            if(buffer) {
                try {
                    _buffer = new File(BUFFER_FILE);
                    BufferedWriter bw = new BufferedWriter(new FileWriter(_buffer));
                    for (IPRange range : _ranges) {
                        for (IPAddress address : rangeToIPList(range)) {
                            bw.write(address + "\n");
                            addresses++;
                        }
                        MainWindow.getInstance().getIPGenerator().moveProgress();
                    }
                    bw.close();
                } catch (IOException ex) {
                    Server.getInstance().log(Console.SYSTEM, "Wystąpił błąd bufora podczas generowania adresów IP!", true);
                }
            } else {
                for(IPRange range : _ranges) {
                    tmpList = rangeToIPList(range);
                    _addresses.addAll(tmpList);
                    MainWindow.getInstance().getIPGenerator().moveProgress();
                    addresses += tmpList.size();
                }
            }
            long time = new Date().getTime() - start.getTime();
            Server.getInstance().log(Console.SYSTEM, "Pomyślnie wygenerowano " + addresses + " adresów IP z " + _ranges.size() + " zakresów w czasie "+Utils.formatTime(time)+"!", false);
            System.out.println("Generated addresses in:\t" + Utils.formatTime(time));
        } else {
            Server.getInstance().log(Console.SYSTEM, "Nie można wygenerować adresów IP, ponieważ nie zdefiniowano zakresów!", true);
        }
        return addresses;
    }
    
    private List<IPAddress> generateIPAddressesForOctet(IPAddress base, int octetId, byte max) {
        List<IPAddress> results = new ArrayList<>();
        IPAddress temp;
        results.add(base);
        for (int i = base.getOctet(octetId)+1 ; i <= (max & 0xFF); i++) {
            temp = base.clone();
            temp.setOctet(octetId, (byte)i);
            results.add(temp);
        }
        return results;
    }
}
