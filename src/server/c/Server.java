package server.c;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import config.c.ConfigurationService;
import config.m.ServerConfiguration;
import core.v.MainWindow;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import server.m.Console;

/**
 *
 * @author mrkaczor
 */
public class Server {

    // <editor-fold defaultstate="collapsed" desc="Object variables">
    private Connection _connection;
    private Console _console;
    private boolean _connectionClosed;
    private InputStream _stderr;
    private InputStream _stdout;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Creating object">
    // <editor-fold defaultstate="collapsed" desc="Singleton">
    public static Server getInstance() {
        return ServerHolder.INSTANCE;
    }

    private static class ServerHolder {
        private static final Server INSTANCE = new Server();
    }
    // </editor-fold>
    
    private Server() {
        _console = new Console();
        _connectionClosed = true;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Object PRIVATE methods">
    private void readStreams() {
        try {
            String line;
            BufferedReader brCleanUp = new BufferedReader(new InputStreamReader(_stdout));
            while ((line = brCleanUp.readLine()) != null) {
                _console.log(Console.MESSAGE, line);
            }
            brCleanUp.close();

            brCleanUp = new BufferedReader(new InputStreamReader(_stderr));
            while ((line = brCleanUp.readLine()) != null) {
                _console.log(Console.ERROR, line);
            }
            brCleanUp.close();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Object PUBLIC methods">
    public boolean connect() {
        ServerConfiguration configuration = ConfigurationService.getInstance().getServerConfiguration();
        boolean result = false;
        
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
                    log(Console.MESSAGE, "Połączono z serwerem!", false);
                    result = true;
                }
            } catch (IOException ex) {
                log(Console.ERROR, ex.getMessage(), true);
            }
        }
        return result;
    }

    public Console console() {
        return _console;
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
            log(Console.ERROR, ex.getMessage(), true);
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
    
    public boolean isConnected() {
        return !_connectionClosed;
    }
    
    public void log(int level, String message, boolean showConsole) {
        console().log(level, message);
        MainWindow.getInstance().refreshServerConsole(showConsole);
    }
    // </editor-fold>
}
