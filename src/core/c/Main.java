package core.c;

import config.c.ConfigurationService;
import config.m.AuthenticationMode;
import config.m.ServerConfiguration;
import core.v.MainWindow;
import hosts.c.HostsService;

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
        
        Server srv = new Server();
        srv.connect();
        
        MainWindow.getInstance().setVisible(true);
        
        srv.disconnect();
        
        System.out.println(HostsService.generateIPLookupCommand("212.77.100.101", 0));
    }
    
    private static void initDefaultConfiguration() {
        ServerConfiguration conf = new ServerConfiguration();
        conf.setName("TEST_SERVER");
        conf.setHost("dev.4meet.com");
        conf.setLogin("mjanik");
        conf.setAuthenticationMode(AuthenticationMode.PRIVATE_KEY);
        conf.setPassword("wanat5");
        conf.setKeyPath("/res/id_rsa");
        ConfigurationService.getInstance().updateServerConfiguration(conf);
        
        ConfigurationService.getInstance().getResourcesConfiguration().setGeoIPPath("~/GeoIP");
        ConfigurationService.getInstance().getResourcesConfiguration().setHostmapPath("~/hostmap");
        ConfigurationService.getInstance().getResourcesConfiguration().setResultsPath("~/results");
    }
}
