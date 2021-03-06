package research.m;

import java.util.LinkedList;
import java.util.List;
import tools.m.IPAddress;

/**
 * Klasa reprezentująca hosta.
 * @author Mateusz
 */
public class HostModelNew {
    private IPAddress _IPAddress;
    private List<String> _discoveredVHosts;
    private String _countryCode;
    private String _countryName;

    /**
     * Tworzy nowego hosta.
     */
    public HostModelNew(){
        this(null,null,null,new LinkedList<String>());
    }

    /**
     * Tworzy nowego hosta o podanym adresie IP.
     * @param IPAddress adres IP hosta
     */
    public HostModelNew(IPAddress address){
        this(address,null,null,new LinkedList<String>());
    }

    /**
     * Tworzy nowego hosta o podanym adresie IP, kodzie oraz pełnej nazwie kraju.
     * @param IPAddress adres IP hosta
     * @param countryCode kod kraju, w którym znajduje się host
     * @param countryName pełna nazwa kraju, w którym znajduje się host
     */
    public HostModelNew(IPAddress address, String countryCode, String countryName){
        this(address,countryCode,countryName,new LinkedList<String>());
    }

    /**
     * Tworzy nowego hosta o podanym adresie IP, kodzie i pełnej nazwie kraju oraz
     * wskazanych wirtualnych hostach.
     * @param IPAddress adres IP hosta
     * @param countryCode kod kraju, w którym znajduje się host
     * @param countryName pełna nazwa kraju, w którym znajduje się host
     * @param discoveredVHosts wirtualne hosty, powiązane z tym hostem podstawowym
     */
    public HostModelNew(IPAddress address, String countryCode, String countryName, List<String> discoveredVHosts){
        _IPAddress = address;
        _countryCode = countryCode;
        _countryName = countryName;
        _discoveredVHosts = discoveredVHosts;        
    }

    /**
     * Zwraca adres IP tego hosta.
     * @return adres IP hosta
     */
    public IPAddress getIPAddress() {
        return _IPAddress;
    }

    /**
     * Ustawia adres IP tego hosta.
     * @param IPAddress adres IP hosta
     */
    public void setIPAddress(IPAddress IPAddress) {
        _IPAddress = IPAddress;
    }

    /**
     * Zwraca listę wirtualnych hostów, wykrytych dla tego hosta podstawowego.
     * @return lista wykrytych wirtualnych hostów
     */
    public List<String> getDiscoveredVHosts() {
        return _discoveredVHosts;
    }

    /**
     * Ustawia listę wirtualnych hostów, wykrytych dla tego hosta podstawowego.
     * Jeżeli host posiadał już listę wirtualnych hostów, zostanie ona nadpisana.
     * @param discoveredVHosts lista wirtualnych hostów
     */
    public void setDiscoveredVHosts(List<String> discoveredVHosts) {
        _discoveredVHosts = discoveredVHosts;
    }

    /**
     * Dodaje pojedynczego wirtualnego hosta do listy wszystkich wykrytych hostów.
     * @param vHost nowy wirtualny host, wykryty dla tego hosta podstawowego
     */
    public void addVHost(String vHost){
        _discoveredVHosts.add(vHost);
    }

    /**
     * Zwraca kod kraju, w którym znajduje się ten host.
     * @return kod kraju, w którym znajduje się host
     */
    public String getCountryCode() {
        return _countryCode;
    }

    /**
     * Ustawia kod kraju, w którym znajduje się ten host.
     * @param countryCode kod kraju
     */
    public void setCountryCode(String countryCode) {
        _countryCode = countryCode;
    }

    /**
     * Zwraca pełną nazwę kraju, w którym znajduje się ten host.
     * @return nazwa kraju, w którym znajduje się host
     */
    public String getCountryName() {
        return _countryName;
    }

    /**
     * Ustawia pełną nazwę kraju, w którym znajduje się ten host.
     * @param countryName nazwa kraju
     */
    public void setCountryName(String countryName) {
        _countryName = countryName;
    }
    
}
