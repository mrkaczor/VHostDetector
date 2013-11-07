package server.m;

import java.util.Date;

/**
 *
 * @author mrkaczor
 */
public class LogMessage {
    private Date _loggedDate;
    private int _logLevel;
    private String _message;
    
    public LogMessage(Date loggedDate, int logLevel, String log) {
        _loggedDate = loggedDate;
        _logLevel = logLevel;
        _message = log;
    }
    
    public Date getDate() {
        return _loggedDate;
    }
    
    public int getLevel() {
        return _logLevel;
    }
    
    public String getMessage() {
        return _message;
    }
}
