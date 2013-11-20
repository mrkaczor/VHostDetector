package config.m;

/**
 * Konfiguracja połączenia z serwerem.
 * @author mrkaczor
 */
public class ServerConfiguration implements Cloneable {
    private String _name;
    private String _host;
    private String _login;
    private String _password;
    private AuthenticationMode _authMode;
    private String _keyPath;

    /**
     * Ustawia nazwę aktualnej konfiguracji (połączenia z serwerem).
     * @param name nazwa aktualnej konfiguracji
     */
    public void setName(String name) {
        this._name = name;
    }

    /**
     * Ustawia nazwę (adres) hosta serwera.
     * @param host nazwa (adres) hosta serwera
     */
    public void setHost(String host) {
        this._host = host;
    }

    /**
     * Ustawia login użytkownika do uwierzytelnienia na serwerze.
     * @param login login użytkownika na serwerze
     */
    public void setLogin(String login) {
        this._login = login;
    }

    /**
     * Ustawia hasło użytkownika lub klucza (w zależności od wyboru sposobu uwierzytelnienia).
     * @param password hasło użytkownika/klucza
     */
    public void setPassword(String password) {
        this._password = password;
    }

    /**
     * Ustawia sposób uwierzytelnienia użytkownika na serwerze.
     * @param mode sposób uwierzytelnienia na serwerze
     */
    public void setAuthenticationMode(AuthenticationMode mode) {
        _authMode = mode;
    }

    /**
     * Ustawie ścieżkę do pliku zawierającego klucz użytkownika do uwierzytelnienia na serwerze.
     * @param keyPath ścieżka do pliku klucza użytkownika
     */
    public void setKeyPath(String keyPath) {
        this._keyPath = keyPath;
    }

    /**
     * Zwraca nazwę aktualnej konfiguracji (serwera).
     * @return nazwa konfiguracji (serwera)
     */
    public String getName() {
        return _name;
    }

    /**
     * Zwraca nazwę (adres) hosta serwera.
     * @return nazwa (adres) hosta serwera
     */
    public String getHost() {
        return _host;
    }

    /**
     * Zwraca login użytkownika, używany do uwierzytelnienia na serwerze.
     * @return nazwa użytkownika
     */
    public String getLogin() {
        return _login;
    }

    /**
     * Zwraca hasło użytkownika lub klucza (w zależności od wyboru sposobu uwierzytelnienia na serwerze).
     * @return hasło użytkownika lub klucza
     */
    public String getPassword() {
        return _password;
    }

    /**
     * Zwraca sposób uwierzytelnienia użytkownika na serwerze.
     * @return sposób uwierzytelnienia na serwerze
     */
    public AuthenticationMode getAuthenticationMode() {
        return _authMode;
    }

    /**
     * Zwraca ścieżkę do pliku klucza użytkownika, używanego do uwierzytelnienia na serwerze.
     * @return ścieżka do pliku klucza użytkownika
     */
    public String getKeyPath() {
        return _keyPath;
    }

    /**
     * Zwraca informację, czy wprowadzona konfiguracja połączenia jest prawidłowa.
     * @return true, jeżeli konfiguracja jest prawidłowa, false w przeciwnym wypadku
     */
    public boolean isValid() {
        if(_host!=null && !_host.equals("") && _password!=null && !_password.equals("")) {
            if(_authMode.equals(AuthenticationMode.PASSWORD) && _login!=null && !_login.equals("")) {
                return true;
            } else if(_authMode.equals(AuthenticationMode.PRIVATE_KEY) && _keyPath!=null && !_keyPath.equals("")) {
                return true;
            }
        }
        return false;
    }

    @Override
    public ServerConfiguration clone() {
        ServerConfiguration conf = new ServerConfiguration();
        conf.setName(_name);
        conf.setHost(_host);
        conf.setLogin(_login);
        conf.setAuthenticationMode(_authMode);
        conf.setPassword(_password);
        conf.setKeyPath(_keyPath);
        return conf;
    }
    
    @Override
    public String toString() {
        String result = "["+super.toString()+"]\n";
        result += "[Name]\t"+_name+"\n";
        result += "[Host]\t"+_host+"\n";
        result += "[Mode]\t"+_authMode+"\n";
        result += "[User]\t"+_login+"\n";
        result += "[Pass]\t"+_password+"\n";
        result += "[Key]\t"+_keyPath;
        return result;
    }
}
