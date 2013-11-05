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

    private ServerConfiguration _configuration;
    private boolean _connectionClosed;
    OutputStream _stdin;
    InputStream _stderr;
    InputStream _stdout;

    public Server() {
        _connectionClosed = true;
    }

    private String generateConnesctionString() {
        String command = "ssh ";
        command += _configuration.getLogin();
        command += "@";
        command += _configuration.getHost();
        return command;
    }
    
    public void refreshConfiguration() {
        _configuration = ConfigurationService.getInstance().getServerConfiguration();
    }

    public ServerConfiguration getCurrentConfiguration() {
        return _configuration;
    }

    public boolean connect() {
        try {
            String line;
            OutputStream stdin = null;
            InputStream stderr = null;
            InputStream stdout = null;

            System.out.println("Trying to connect: " + generateConnesctionString());

            Connection conn = new Connection(_configuration.getHost());
            conn.connect();
            if(conn.authenticateWithPublicKey(_configuration.getLogin(), new File("C:/Users/mrkaczor/Documents/GitHub/VHostDetector/res/id_rsa"), _configuration.getPassword())) {
                Session sess = conn.openSession();

                sess.execCommand("pwd");
                _stdout = new StreamGobbler(sess.getStdout());
                _stderr = new StreamGobbler(sess.getStderr());
                
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
                
                sess.close();
            }
            conn.close();
            
            /*
            
            // launch EXE and grab stdin/stdout and stderr
            Process process = Runtime.getRuntime().exec("cmd /C C:\\Progra~2\\Git\\bin\\sh.exe");
            
            stdin = process.getOutputStream();
            stderr = process.getErrorStream();
            stdout = process.getInputStream();

            
            // "write" the parms into stdin
//            line = "sh\n";
//            stdin.write(line.getBytes());
//            stdin.flush();
//
            line = "ssh";
            stdin.write(line.getBytes());
            stdin.flush();

            stdin.close();
            
            
            // clean up if any output in stdout
            BufferedReader brCleanUp =
                    new BufferedReader(new InputStreamReader(stdout));
            System.out.println("\nOUTPUT:");
            while ((line = brCleanUp.readLine()) != null) {
                System.out.println ("[Stdout] " + line);
            }
            brCleanUp.close();

            // clean up if any output in stderr
            brCleanUp =
                    new BufferedReader(new InputStreamReader(stderr));
            System.out.println("\nERRORS:");
            while ((line = brCleanUp.readLine()) != null) {
                System.out.println ("[Stderr] " + line);
            }
            brCleanUp.close();

            return true;
            /*
            
            
             if(_configuration != null && _connectionClosed) {
             Process process;
             try {
             System.out.println("Trying to connect: "+generateConnesctionString());
             process = Runtime.getRuntime().exec(generateConnesctionString());
             _stdin = process.getOutputStream();
             _stderr = process.getErrorStream();
             _stdout = process.getInputStream();
                    
             //////
             String line;
             BufferedReader brCleanUp =
             new BufferedReader(new InputStreamReader(_stdout));
             while ((line = brCleanUp.readLine()) != null) {
             //System.out.println ("[Stdout] " + line);
             }
             brCleanUp.close();

             // clean up if any output in stderr
             brCleanUp =
             new BufferedReader(new InputStreamReader(_stderr));
             while ((line = brCleanUp.readLine()) != null) {
             System.err.println ("[Stderr] " + line);
             }
             brCleanUp.close();
             //////
                    
             _stdin.close();
             _stdout.close();
             _stderr.close();
                    
             _connectionClosed = false;
             } catch (IOException ex) {
             return false;
             }
             return true;
             }
             return false;
             */
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        return false;
    }

    public boolean disconnect() {
        if (_configuration != null && !_connectionClosed) {

            _connectionClosed = true;
            return true;
        }
        return false;
    }
}
