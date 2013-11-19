package research.m;

import java.util.ArrayList;
import java.util.List;

/**
 * Kontener przechowujący dane hostów.
 * @author Mateusz
 */
public class HostsHolder {
    private List<HostModel> _hosts;

    /**
     * Tworzy nowy kontener na dane hostów.
     */
    public HostsHolder() {
        _hosts = new ArrayList<>();
    }

    /**
     * Dodaje podany host do kontenera.
     * @param host host, którego dane mają być zachowane w kontenerze
     */
    public void addHost(HostModel host){
        _hosts.add(host);
    }

    /**
     * Zwraca dane hosa o podanym adresie IP.
     * @param IPAddress adres IP hosta
     * @return dane hosta, jeżeli host o podanym adresie IP znajduje się w kontenerze, null w przeciwnym wyadku
     */
    public HostModel getHost(String IPAddress){
        HostModel result = null;
                
        for (HostModel host : _hosts) {
            if(IPAddress.equals(host.getIPAddress())){
                result = host;
                break;
            }
        }
        
        return result;
    }

    /**
     * Zwraca dane wszystkich hostów, które są zapisane w kontenerze.
     * @return dane wszystkich hostów znajdujących się w kontenerze
     */
    public List<HostModel> getHosts() {
        return _hosts;
    }

    /**
     * Wczytuje dane podanych hostów do kontenera. Jeżeli w kontenerze znajdowały się już jakieś
     * hosty, ich dane zostaną nadpisane.
     * @param hosts lista hostów, których dane mają być zapisane w kontenerze
     */
    public void setHosts(List<HostModel> hosts) {
        _hosts = hosts;
    }

    /**
     * Zwraca całkowitą liczbę hostów, których dane są zapisane w kontenerze.
     * @return liczba hostów znajdujących się w kontenerze
     */
    public int getServersCount() {
        return _hosts.size();
    }
}
