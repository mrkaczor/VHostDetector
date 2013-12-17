package core.c;

import config.c.ConfigurationService;
import config.m.AuthenticationMode;
import config.m.ServerConfiguration;
import core.v.MainWindow;
import tools.c.IPValidator;
import tools.m.IPAddress;

/**
 *
 * @author mrkaczor
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        initDefaultConfiguration();
        
        //Server srv = Server.getInstance();
        //srv.connect();
        
        //IPValidator.getInstance().validateIP(new IPAddress("77.252.2.84"));
        //IPValidator.getInstance().validateIP(new IPAddress("157.42.16.1"));
        MainWindow.getInstance().setVisible(true);
        
//        srv.executeCommand("cd ~/GeoIP/ && pwd && perl IPtoLocation.pl 212.77.100.101 > a2.txt");
//        srv.executeCommand("pwd");
//        srv.executeCommand("./IPtoLocation.pl 212.77.100.101");
//        srv.executeCommand("cd");
        
        //srv.disconnect();
        
        //System.out.println(HostsService.generateIPLookupCommand("212.77.100.101", 0));
    }
    
    private static void initDefaultConfiguration() {
        ServerConfiguration conf = new ServerConfiguration();
        conf.setName("TEST_SERVER");
        conf.setHost("dev.4meet.com");
        conf.setLogin("mjanik");
        conf.setAuthenticationMode(AuthenticationMode.PRIVATE_KEY);
        conf.setPassword("wanat5");
        conf.setKeyPath("res/fourmeet");
        ConfigurationService.getInstance().updateServerConfiguration(conf);
        
        ConfigurationService.getInstance().getResourcesConfiguration().setHostsListFilePath("res/hosts.txt");
        ConfigurationService.getInstance().getResourcesConfiguration().setGeoIPPath("~/GeoIP");
        ConfigurationService.getInstance().getResourcesConfiguration().setHostmapPath("~/hostmap");
        ConfigurationService.getInstance().getResourcesConfiguration().setResearchPath("~/results");
    }
}
