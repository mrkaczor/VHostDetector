package hosts.m;

import java.util.List;

/**
 *
 * @author Mateusz
 */
public class HostsHolder {
    private List<String> _IPAddresses;
    private List<HostModel> _hosts;

    public List<String> getIPAddresses() {
        return _IPAddresses;
    }

    public void setIPAddresses(List<String> IPAddresses) {
        _IPAddresses = IPAddresses;
    }
    
    public void addIPAddress(String IPAddress){
        _IPAddresses.add(IPAddress);
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
    
    public void addHost(HostModel host){
        _hosts.add(host);
    }

    public List<HostModel> getHosts() {
        return _hosts;
    }

    public void setHosts(List<HostModel> hosts) {
        _hosts = hosts;
    }
    
    public int getServersCount() {
        return _IPAddresses.size();
    }
}
