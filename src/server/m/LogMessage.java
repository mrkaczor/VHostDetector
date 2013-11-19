package server.m;

import java.util.Date;

/**
 * Wiadomość wysyłana na konsolę.
 * @author mrkaczor
 */
public class LogMessage {
    private Date _loggedDate;
    private int _logLevel;
    private String _message;

    /**
     * Tworzy nową wiadomość przeznaczoną do przesłania na konsolę.
     * @param loggedDate data przesyłanej wiadomości
     * @param logLevel poziom, na którym zgłaszana jest wiadomość
     * @param log treść wiadomości
     */
    public LogMessage(Date loggedDate, int logLevel, String log) {
        _loggedDate = loggedDate;
        _logLevel = logLevel;
        _message = log;
    }

    /**
     * Zwraca datę utworzenia wiadomości.
     * @return data utworzenia loga
     */
    public Date getDate() {
        return _loggedDate;
    }

    /**
     * Zwraca poziom, na którym została zgłoszona wiadomość.
     * @return poziom loga
     */
    public int getLevel() {
        return _logLevel;
    }

    /**
     * Zwraca treść wiadomości.
     * @return treść loga
     */
    public String getMessage() {
        return _message;
    }
}
