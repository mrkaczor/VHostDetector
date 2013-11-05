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

/**
 *
 * @author mrkaczor
 */
public class Server {

    Connection _connection;
    Session _session;
    private boolean _connectionClosed;
    InputStream _stderr;
    InputStream _stdout;

    public Server() {
        _connectionClosed = true;
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
                    _session = _connection.openSession();

                    _stdout = new StreamGobbler(_session.getStdout());
                    _stderr = new StreamGobbler(_session.getStderr());

                    BufferedReader brCleanUp =
                            new BufferedReader(new InputStreamReader(_stdout));
                    while ((line = brCleanUp.readLine()) != null) {
                        System.err.println("[Stdout] " + line);
                    }
                    brCleanUp.close();

                    brCleanUp =
                            new BufferedReader(new InputStreamReader(_stderr));
                    while ((line = brCleanUp.readLine()) != null) {
                        System.err.println("[Stderr] " + line);
                    }
                    brCleanUp.close();                    
                }
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }
        return false;
    }

    public void executeCommand(String command) {
        try {
            _session.execCommand(command);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public boolean disconnect() {
        if (_connection != null && !_connectionClosed) {
            _session.close();
            _connection.close();
            _connection = null;
            _connectionClosed = true;
            return true;
        }
        return false;
    }
}
