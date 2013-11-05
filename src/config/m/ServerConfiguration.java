package config.m;

/**
 *
 * @author mrkaczor
 */
public class ServerConfiguration implements Cloneable {
    private String _name;
    private String _host;
    private String _login;
    private String _password;
    private AuthenticationMode _authMode;

    public void setName(String _name) {
        this._name = _name;
    }

    public void setHost(String _host) {
        this._host = _host;
    }

    public void setLogin(String _login) {
        this._login = _login;
    }

    public void setPassword(String _password) {
        this._password = _password;
    }
    
    public void setAuthenticationMode(AuthenticationMode mode) {
        _authMode = mode;
    }

    public String getName() {
        return _name;
    }

    public String getHost() {
        return _host;
    }

    public String getLogin() {
        return _login;
    }

    public String getPassword() {
        return _password;
    }
    
    public AuthenticationMode getAuthenticationMode() {
        return _authMode;
    }

    @Override
    public ServerConfiguration clone() {
        ServerConfiguration conf = new ServerConfiguration();
        conf.setName(_name);
        conf.setHost(_host);
        conf.setLogin(_login);
        conf.setPassword(_password);
        return conf;
    }
}
