package hosts.c;

import config.c.ConfigurationService;
import config.m.ResourcesConfiguration;
import hosts.m.HostsHolder;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Mateusz
 */
public class HostsService {
    
    private HostsHolder _initialHosts;

    // <editor-fold defaultstate="collapsed" desc="Creating object">
    // <editor-fold defaultstate="collapsed" desc="Singleton">
    public static HostsService getInstance() {
        return HostsServiceHolder.INSTANCE;
    }

    private static class HostsServiceHolder {
        private static final HostsService INSTANCE = new HostsService();
    }
    // </editor-fold>
    
    private HostsService() {
        _initialHosts = new HostsHolder();
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Object PRIVATE methods">
    private void readHostsIPs() {
        String filePath = ConfigurationService.getInstance().getResourcesConfiguration().getHostsListFilePath();
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(filePath)));
            String line;
            while ((line = br.readLine()) != null) {
                _initialHosts.addIPAddress(line);
            }
        } catch(IOException ex) {
            System.err.println("Unable to load servers data from given directory due to exception:\n"+ex.getMessage());
        }
    }

    private String generateIPLookupCommand(String IPAddress, int timeout) {
        ResourcesConfiguration config = ConfigurationService.getInstance().getResourcesConfiguration();
        String geoIP = config.getGeoIPPath();
        String hostmap = config.getHostmapPath();
        String results = config.getResultsPath();

        if (timeout > 0) {
            return String.format("cd %3$s/ "
                    + "&& ./IPtoLocation.pl %1$s > %5$s/%1$s "
                    + "&& cd "
                    + "&& timeout %2$ds ruby %4$s/hostmap.rb -t %1$s >> %5$s/%1$s "
                    + "&& echo %1$s >> %5$s/completed",
                    IPAddress, timeout, geoIP, hostmap, results);
        } else {
            return String.format("cd %2$s/ "
                    + "&& ./IPtoLocation.pl %1$s > %4$s/%1$s "
                    + "&& cd "
                    + "&& ruby %3$s/hostmap.rb -t %1$s >> %4$s/%1$s "
                    + "&& echo %1$s >> %4$s/completed",
                    IPAddress, geoIP, hostmap, results);
        }
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Object PUBLIC methods">
    public void detectVirtualHosts() {
        readHostsIPs();
        System.out.println("SUCCESSFULLY loaded "+_initialHosts.getServersCount()+" servers!");
        for(String IPAddress : _initialHosts.getIPAddresses()) {
            System.out.println(generateIPLookupCommand(IPAddress, 600));
        }
    }
    // </editor-fold>
    
}
