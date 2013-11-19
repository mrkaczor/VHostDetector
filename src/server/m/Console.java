package server.m;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Konsola przechowująca wszystkie logi systemu (w tym odpowiedzi zdalnego serwera).
 * @author mrkaczor
 */
public class Console {
    public static final int MESSAGE = 0;
    public static final int ERROR = 1;
    public static final int SYSTEM = 2;
    
    private List<LogMessage> _logs;
    
    public Console() {
        _logs = new ArrayList<>();
    }

    /**
     * Dodaje (na określonym poziomie) nową wiadomość do konsoli.
     * @param level poziom, na którym ma być zalogowana wiadomość
     * @param log treść wiadomości
     */
    public void log(int level, String log) {
        if(level == ERROR || level == MESSAGE || level == SYSTEM) {
            _logs.add(new LogMessage(Calendar.getInstance().getTime(), level, log));
        } else {
            _logs.add(new LogMessage(Calendar.getInstance().getTime(), Console.MESSAGE, log));
        }
    }

    /**
     * Zwraca wszystkie logi zapisane na konsoli.
     * @return logi zawarte w konsoli
     */
    public List<LogMessage> getLogs() {
        return _logs;
    }

    /**
     * Zwraca całkowitą ilość logów zapisanych na konsoli.
     * @return ilość logów zapisanych na konsoli
     */
    public int logCount() {
        return _logs.size();
    }
}
