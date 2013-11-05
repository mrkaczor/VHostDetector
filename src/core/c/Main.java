package core.c;

import config.m.ServerConfiguration;
import core.v.MainWindow;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        srv.loadConfiguration(conf);
        
        srv.connect();
        
        MainWindow.getInstance().setVisible(true);
        
        System.out.println("SIEMANO");
        
        srv.disconnect();
    }
}
