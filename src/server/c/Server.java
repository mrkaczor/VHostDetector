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
 * Serwis zarządzający interakcją ze zdalnym serwerem.
 * @author mrkaczor
 */
public class Server {

    // <editor-fold defaultstate="collapsed" desc="Object variables">
    private Connection _connection;
    private Console _console;
    private boolean _connectionClosed;
    private List<String> _stderrBuffer;
    private List<String> _stdoutBuffer;
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
    private int readInfoStreams(InputStream stream, boolean enableLog) {
        _stdoutBuffer = new ArrayList<>();
        try {
            String line;
            BufferedReader brCleanUp = new BufferedReader(new InputStreamReader(stream));
            while ((line = brCleanUp.readLine()) != null) {
                _stdoutBuffer.add(line);
            }
            brCleanUp.close();
            if(enableLog) {
                for(String log : _stdoutBuffer) {
                    _console.log(Console.MESSAGE, log);
                }
            }
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        return _stdoutBuffer.size();
    }
    
    private int readErrorStreams(InputStream stream, boolean enableLog) {
        _stderrBuffer = new ArrayList<>();
        try {
            String line;
            BufferedReader brCleanUp = new BufferedReader(new InputStreamReader(stream));
            while ((line = brCleanUp.readLine()) != null) {
                _stderrBuffer.add(line);
            }
            brCleanUp.close();
            if(enableLog) {
                for(String log : _stderrBuffer) {
                    _console.log(Console.ERROR, log);
                }
            }
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        return _stderrBuffer.size();
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Object PUBLIC methods">
    /**
     * Otwiera nowe połączenie z serwerem, na podstawie zdefiniowanej konfiguracji.
     * @return true, jeżeli udało się pomyślnie połączyć z serwerem, false w przeciwnym wypadku
     */
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

    /**
     * Zwraca instancję konsoli.
     * @return konsola
     */
    public Console console() {
        return _console;
    }

    /**
     * Wykonuje podaną komendę na zdalnej maszynie i wyświetla wynik wywołania na konsoli.
     * @param command komenda, która ma być wykonana na zdalnej maszynie
     * @return true, jeżeli wykonanie komendy nie spowoduje wystąpienia błędów, false w przeciwnym wypadku
     */
    public boolean executeCommand(String command) {
        return executeCommand(command, true);
    }

    /**
     * Wykonuje podaną komendę na zdalnej maszynie i (w przypadku ustawienia flagi logOutput) wyświetla wynik wywołania na konsoli.
     * @param command komenda, która ma być wykonana na zdalnej maszynie
     * @param logOutput flaga determinująca, czy wynik wywołania komendy ma być wyświetlony na konsoli
     * @return true, jeżeli wykonanie komendy nie spowoduje wystąpienia błędów, false w przeciwnym wypadku
     */
    public boolean executeCommand(String command, boolean logOutput) {
        if(!_connectionClosed && _connection != null) {
            if(logOutput) {
                log(Console.SYSTEM, "Executing command:\t"+command, false);
            } else {
                System.out.println("Executing command:\t"+command);
            }
            Session session;
            InputStream outputStream;
            int outputCount = 0;
            int errorCount = 0;
            try {
                session = _connection.openSession();
                session.execCommand(command);
                System.out.println("Command executed!");
                
                outputStream = new StreamGobbler(session.getStdout());
                outputCount = readInfoStreams(outputStream, logOutput);
                
                outputStream = new StreamGobbler(session.getStderr());
                errorCount = readErrorStreams(outputStream, logOutput);
                
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

    /**
     * Zamyka aktualne połączenie z serwerem.
     * @return true, jeżeli pomyślnie zamknięto połączenie, w przeciwnym razie false (np. jeśli połączenie jest już zamknięte)
     */
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

    /**
     * Zwraca informację, czy istnieje aktywne połączenie do serwera.
     * @return true, jeżeli istnieje połączenie z serwerem, w przeciwnym razie false
     */
    public boolean isConnected() {
        return !_connectionClosed;
    }

    /**
     * Zwraca bufor zawierający odpowiedź serwera na ostatnią wykonaną komendę.
     * @return odpowiedź serwera na ostatnią wywołaną komendę
     */
    public List<String> readOutputBuffer() {
        return _stdoutBuffer;
    }

    /**
     * Zwraca bufor zawierający informacje o błędach powstałych w wyniku wykonania ostatniej komendy doserwera.
     * @return błędy powstałe na serwerze w wyniku ostatnio wywołanej komendy
     */
    public List<String> readErrorBuffer() {
        return _stderrBuffer;
    }

    /**
     * Wyświetla podaną wiadomość na konsoli.
     * @param level poziom wiadomości
     * @param message treść wiadomości
     * @param showConsole flaga informująca, czy konsola ma być automatycznie wyświetlona po wypisaniu wiadomości
     */
    public void log(int level, String message, boolean showConsole) {
        console().log(level, message);
        MainWindow.getInstance().refreshServerConsole(showConsole);
    }
    // </editor-fold>
}
