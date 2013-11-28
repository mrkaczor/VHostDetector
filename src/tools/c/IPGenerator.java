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

    private List<IPRange> _ranges;
    private List<IPAddress> _addresses;

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
    }

    public boolean transferAddressesToReaserch() {
        if(_addresses != null && !_addresses.isEmpty()) {
            Date start = new Date();
            for(IPAddress address : _addresses) {
                if(!HostsService.getInstance().loadServerData(address.toString())) {
                    return false;
                }
                MainWindow.getInstance().getIPGenerator().moveProgress();
            }
            System.out.println("Transferred addresses in:\t"+Utils.formatTime(new Date().getTime()-start.getTime()));
            return true;
        }
        return false;
    }

    public long getAddressesCount() {
        return _addresses.size();
    }

    public List<IPRange> getRanges() {
        return _ranges;
    }

    public void setRanges(List<IPRange> ranges) {
        _ranges = ranges;
    }

    public boolean exportAddresses(File file) {
        BufferedWriter bw;
        try {
            bw = new BufferedWriter(new FileWriter(file));
            for(IPAddress address : _addresses) {
                bw.write(address + "\n");
            }
            bw.close();
        } catch (IOException ex) {
            Server.getInstance().log(Console.SYSTEM, "Nie udało się wyeksportować danych adresów IP do pliku z powodu błędu:\n" + ex.getMessage(), false);
            return false;
        }
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
        List<IPAddress> temp_addresses = generateIPAddressesForOctet(base, 1, range.getOctetMaxValue(1));
        for (IPAddress addr : temp_addresses) {
            List<IPAddress> temp_addresses2 = generateIPAddressesForOctet(addr, 2, range.getOctetMaxValue(2));
            for (IPAddress addr2 : temp_addresses2) {
                List<IPAddress> temp_addresses3 = generateIPAddressesForOctet(addr2, 3, range.getOctetMaxValue(3));
                for (IPAddress addr3 : temp_addresses3) {
                    List<IPAddress> final_addresses = generateIPAddressesForOctet(addr3, 4, range.getOctetMaxValue(4));
                    for (IPAddress addr4 : final_addresses) {
                        addresses.add(addr4);
                    }
                }
            }
        }

        return addresses;
    }

    public long rangesToIPList() {
        long addresses = 0;
        List<IPAddress> tmpList;
        if(!_ranges.isEmpty()) {
            Server.getInstance().log(Console.SYSTEM, "Generowanie adresów IP z " + _ranges.size() + " zakresów...", false);
            _addresses.clear();
            Date start = new Date();
            for(IPRange range : _ranges) {
                tmpList = rangeToIPList(range);
                _addresses.addAll(tmpList);
                MainWindow.getInstance().getIPGenerator().moveProgress();
                addresses += tmpList.size();
            }
            long time = new Date().getTime() - start.getTime();
            Server.getInstance().log(Console.SYSTEM, "Pomyślnie wygenerowano " + _addresses.size() + " adresów IP z " + _ranges.size() + " zakresów w czasie "+Utils.formatTime(time)+"!", false);
            System.out.println("Generated addresses in:\t" + Utils.formatTime(time));
        } else {
            Server.getInstance().log(Console.SYSTEM, "Nie można wygenerować adresów IP, ponieważ nie zdefiniowano zakresów!", true);
        }
        return addresses;
    }
    
    private List<IPAddress> generateIPAddressesForOctet(IPAddress base, int octetId, int max) {
        List<IPAddress> results = new ArrayList<>();
        IPAddress temp;
        results.add(base);
        for (int i = base.getOctet(octetId) + 1; i <= max; i++) {
            temp = base.clone();
            temp.setOctet(octetId, i);
            results.add(temp);
        }
        return results;
    }
}
