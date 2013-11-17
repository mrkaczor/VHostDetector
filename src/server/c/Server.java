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
import java.util.ArrayList;
import java.util.List;
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
    private int readInfoStreams() {
        int lineCount = 0;
        try {
            String line;
            BufferedReader brCleanUp = new BufferedReader(new InputStreamReader(_stdout));
            while ((line = brCleanUp.readLine()) != null) {
                lineCount++;
                _console.log(Console.MESSAGE, line);
            }
            brCleanUp.close();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        return lineCount;
    }
    
    private int readErrorStreams() {
        int lineCount = 0;
        try {
            String line;
            BufferedReader brCleanUp = new BufferedReader(new InputStreamReader(_stderr));
            while ((line = brCleanUp.readLine()) != null) {
                lineCount++;
                _console.log(Console.ERROR, line);
            }
            brCleanUp.close();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        return lineCount;
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
                    log(Console.SYSTEM, "Połączono z serwerem '" + configuration.getHost() + "'!", false);
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
    
    public boolean executeCommand(String command) {
        return executeCommand(command, true);
    }

    public boolean executeCommand(String command, boolean logOutput) {
        if(!_connectionClosed && _connection != null) {
            if(logOutput) {
                log(Console.SYSTEM, "Executing command:\t"+command, false);
            }
            Session session;
            int outputCount = 0;
            int errorCount = 0;
            try {
                session = _connection.openSession();
                session.execCommand(command);
                System.out.println("Command executed!");
                _stdout = new StreamGobbler(session.getStdout());
                _stderr = new StreamGobbler(session.getStderr());
                if(logOutput) {
                    outputCount = readInfoStreams();
                    errorCount = readErrorStreams();
                }
                session.close();
                return errorCount == 0;
            } catch (IOException ex) {
                String errorMsg = ex.getMessage();
                if(!logOutput) {
                    errorMsg = "Error executing command:\t" + command + "\n\t" + errorMsg;
                }
                log(Console.ERROR, errorMsg, true);
            }
        }
        return false;
    }
    
    public boolean disconnect() {
        if (_connection != null && !_connectionClosed) {
            _connection.close();
            _connection = null;
            _connectionClosed = true;
            log(Console.SYSTEM, "Rozłączono z serwerem!", false);
            return true;
        }
        return false;
    }
    
    public boolean isConnected() {
        return !_connectionClosed;
    }

    public List<String> readOutputBuffer() {
        List<String> output = new ArrayList<>();
        try {
            BufferedReader brCleanUp = new BufferedReader(new InputStreamReader(_stdout));
            String line;
            while ((line = brCleanUp.readLine()) != null) {
                output.add(line);
            }
            brCleanUp.close();
        } catch (IOException ex) {
            System.err.println("Unable to read output buffer due to exception: "+ex.getMessage());
        }
        return output;
    }

    public List<String> readErrorBuffer() {
        List<String> output = new ArrayList<>();
        try {
            BufferedReader brCleanUp = new BufferedReader(new InputStreamReader(_stderr));
            String line;
            while ((line = brCleanUp.readLine()) != null) {
                output.add(line);
            }
            brCleanUp.close();
        } catch (IOException ex) {
            System.err.println("Unable to read error buffer due to exception: "+ex.getMessage());
        }
        return output;
    }

    public void log(int level, String message, boolean showConsole) {
        console().log(level, message);
        MainWindow.getInstance().refreshServerConsole(showConsole);
    }
    // </editor-fold>
}
