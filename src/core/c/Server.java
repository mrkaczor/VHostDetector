package core.c;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import config.c.ConfigurationService;
import config.m.ServerConfiguration;
import java.io.BufferedReader;
import java.io.File;
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
public class Server {

    Connection _connection;
    private boolean _connectionClosed;
    InputStream _stderr;
    InputStream _stdout;

    public Server() {
        _connectionClosed = true;
    }
    
    private void readStreams() {
        try {
            BufferedReader brCleanUp = new BufferedReader(new InputStreamReader(_stdout));
            String line;
            
            while ((line = brCleanUp.readLine()) != null) {
                System.err.println("[Stdout] " + line);
            }
            brCleanUp.close();

            brCleanUp = new BufferedReader(new InputStreamReader(_stderr));
            while ((line = brCleanUp.readLine()) != null) {
                System.err.println("[Stderr] " + line);
            }
            brCleanUp.close();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public boolean connect() {
        ServerConfiguration configuration = ConfigurationService.getInstance().getServerConfiguration();
        String line;
        
        if(configuration != null && _connectionClosed) {
            try {
                _connection = new Connection(configuration.getHost());
                _connection.connect();

                switch(configuration.getAuthenticationMode()) {
                    case PASSWORD:
                        _connectionClosed = !_connection.authenticateWithPassword(configuration.getLogin(), configuration.getPassword());
                        break;
                    case PRIVATE_KEY:
                        _connectionClosed = !_connection.authenticateWithPublicKey(configuration.getLogin(), new File(configuration.getKeyPath()), configuration.getPassword());
                        break;
                    default:
                        _connectionClosed = true;
                }

                if(!_connectionClosed) {
                    System.out.println("SUCCESSFULLY CONNECTED!");
                              
                    executeCommand("pwd");
                }
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }
        return false;
    }

    public void executeCommand(String command) {
        Session session;
        try {
            session = _connection.openSession();
            session.execCommand(command);
            _stdout = new StreamGobbler(session.getStdout());
            _stderr = new StreamGobbler(session.getStderr());
            session.close();
            readStreams();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public boolean disconnect() {
        if (_connection != null && !_connectionClosed) {
            _connection.close();
            _connection = null;
            _connectionClosed = true;
            return true;
        }
        return false;
    }
}
