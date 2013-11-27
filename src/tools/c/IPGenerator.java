package tools.c;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import tools.m.IPAddress;
import tools.m.IPRange;

/**
 * Serwis służący do generowania adresów IP serwerów.
 *
 * @author mrkaczor
 */
public class IPGenerator {

    private List<IPRange> _ranges;

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
    }
    // </editor-fold>
    
    public List<IPRange> getRanges() {
        return _ranges;
    }

    public void setRanges(List<IPRange> ranges) {
        _ranges = ranges;
    }

    public int loadRanges(File[] files) {
        int rangesCount = 0;
        for(File file : files) {
            rangesCount += loadRanges(file);
        }
        return rangesCount;
    }

    public int loadRanges(File file) {
        int rangesCount = 0;
        
        return rangesCount;
    }

    public long calculatePossibleAddresses(IPRange range) {
        int constOctets = range.getMask() / 8;
        long count = 1;
        int temp;
        for(int i=constOctets; i<4; i++) {
            temp = range.getMask() - i * constOctets;
            count *= 1 << (temp>=0 ? 8-temp : 8);
        }
        return count;
    }

    public List<String> rangeToIPList(IPRange range) throws IllegalArgumentException {
        List<String> addresses = new ArrayList<>();

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
                        addresses.add(addr4.toString());
                    }
                }
            }
        }

        return addresses;
    }

    public List<String> rangesToIPList(List<IPRange> ranges) {
        List<String> IPList = new ArrayList<>();
        for(IPRange range : ranges) {
            IPList.addAll(rangeToIPList(range));
        }
        return IPList;
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
