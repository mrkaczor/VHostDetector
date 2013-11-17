package research.c;

import config.c.ConfigurationService;
import config.m.ResourcesConfiguration;
import research.m.HostsHolder;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import research.m.HostModel;
import server.c.Server;
import server.m.Console;

/**
 *
 * @author Mateusz
 */
public class HostsService {
    
    private HostsHolder _hosts;

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
        _hosts = new HostsHolder();
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Object PRIVATE methods">
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Object PUBLIC methods">
    public boolean loadServersData() {
        String filePath = ConfigurationService.getInstance().getResourcesConfiguration().getHostsListFilePath();
        if(filePath != null && !filePath.equals("")) {
            _hosts.getHosts().clear();
            try {
                BufferedReader br = new BufferedReader(new FileReader(new File(filePath)));
                String line;
                HostModel host;
                while ((line = br.readLine()) != null) {
                    host = new HostModel(line);
                    _hosts.addHost(host);
                }
                Server.getInstance().log(Console.SYSTEM, "Successfully loaded "+_hosts.getServersCount()+" servers data!", false);
                return true;
            } catch(IOException ex) {
                Server.getInstance().log(Console.SYSTEM, "Wystąpił błąd podczas próby wczytania danych serwerów: "+ex.getMessage(), true);
                System.err.println("Wystąpił błąd podczas próby wczytania danych serwerów:\n"+ex.getMessage());
            }
        } else {
            Server.getInstance().log(Console.SYSTEM, "Nie skonfigurowano ścieżki pliku z danymi serwerów!", true);
        }
        return false;
    }

    public String generateIPLookupCommand(String IPAddress, int timeout) {
        ResourcesConfiguration config = ConfigurationService.getInstance().getResourcesConfiguration();
        String geoIP = config.getGeoIPPath();
        String hostmap = config.getHostmapPath();
        String results = config.getResearchPath();
        String output = config.getCompletionListFile();

        if (timeout > 0) {
            return String.format("cd %3$s/ "
                    + "&& ./IPtoLocation.pl %1$s > %5$s/%1$s "
                    + "&& cd "
                    + "&& perl -e \"alarm %2$d; exec @ARGV\" \"ruby %4$s/hostmap.rb -t %1$s >> %5$s/%1$s\" "
                    + "&& echo %1$s >> %5$s/%6$s",
                    IPAddress, timeout, geoIP, hostmap, results, output);
        } else {
            return String.format("cd %2$s/ "
                    + "&& ./IPtoLocation.pl %1$s > %4$s/%1$s "
                    + "&& cd "
                    + "&& ruby %3$s/hostmap.rb -t %1$s >> %4$s/%1$s "
                    + "&& echo %1$s >> %4$s/%6$s",
                    IPAddress, geoIP, hostmap, results, output);
        }
    }

    public HostsHolder getHostsData() {
        return _hosts;
    }
    // </editor-fold>

}
