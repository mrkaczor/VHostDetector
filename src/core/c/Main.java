package core.c;

import config.c.ConfigurationService;
import config.m.ServerConfiguration;
import core.v.MainWindow;

/**
 *
 * @author mrkaczor
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ServerConfiguration conf = new ServerConfiguration();
        conf.setHost("dev.4meet.com");
        conf.setLogin("mjanik");
        conf.setPassword("wanat5");
        
        Server srv = new Server();
        ConfigurationService.getInstance().updateServerConfiguration(conf);
        
        srv.refreshConfiguration();
        srv.connect();
        
        MainWindow.getInstance().setVisible(true);
        
        System.out.println("SIEMANO");
        
        srv.disconnect();
    }
}
