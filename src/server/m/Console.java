package server.m;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author mrkaczor
 */
public class Console {
    public static final int MESSAGE = 0;
    public static final int ERROR = 0;
    
    private List<LogMessage> _logs;
    
    public Console() {
        _logs = new ArrayList<>();
    }
    
    public void log(int level, String log) {
        if(level == ERROR || level == MESSAGE) {
            _logs.add(new LogMessage(Calendar.getInstance().getTime(), level, log));
        } else {
            _logs.add(new LogMessage(Calendar.getInstance().getTime(), Console.MESSAGE, log));
        }
    }
    
    public List<LogMessage> getLogs() {
        return _logs;
    }
    
    public int logCount() {
        return _logs.size();
    }
}
