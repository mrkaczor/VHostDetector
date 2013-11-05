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
    
    public static AuthenticationMode getByName(String name) {
        for(AuthenticationMode am : AuthenticationMode.values()) {
            System.out.print("Compare:\t"+name+" =?= "+am._authenticationName+"  -->  ");
            if(am._authenticationName.equals(name)) {
                System.out.println("TRUE");
                return am;
            } else {
                System.out.println("FALSE");
            }
        }
        return null;
    }
    
    private String _authenticationName;
}
