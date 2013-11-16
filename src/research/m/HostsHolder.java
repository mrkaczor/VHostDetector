package research.m;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mateusz
 */
public class HostsHolder {
    private List<HostModel> _hosts;

    public HostsHolder() {
        _hosts = new ArrayList<>();
    }

    public void addHost(HostModel host){
        _hosts.add(host);
    }

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
    
    public List<HostModel> getHosts() {
        return _hosts;
    }

    public void setHosts(List<HostModel> hosts) {
        _hosts = hosts;
    }
    
    public int getServersCount() {
        return _hosts.size();
    }
}
