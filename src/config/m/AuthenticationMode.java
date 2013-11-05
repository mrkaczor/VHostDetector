package config.m;

/**
  Class providing all available authentication modes to server instance.
 * @author mrkaczor
 */
public enum AuthenticationMode {
    PASSWORD("Nazwa użytkownika i hasło"), PRIVATE_KEY("Klucz prywatny");
    
    private AuthenticationMode(String name) {
        _authenticationName = name;
    }
    
    @Override
    public String toString() {
        return _authenticationName;
    }

    private String _authenticationName;
}
