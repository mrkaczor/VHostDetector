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
    private String _keyPath;

    public void setName(String name) {
        this._name = name;
    }

    public void setHost(String host) {
        this._host = host;
    }

    public void setLogin(String login) {
        this._login = login;
    }

    public void setPassword(String password) {
        this._password = password;
    }
    
    public void setAuthenticationMode(AuthenticationMode mode) {
        _authMode = mode;
    }

    public void setKeyPath(String keyPath) {
        this._keyPath = keyPath;
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

    public String getKeyPath() {
        return _keyPath;
    }

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
