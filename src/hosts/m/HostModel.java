/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hosts.m;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Mateusz
 */
public class HostModel {
    private String _IPAddress;
    private List<String> _discoveredVHosts;
    private String _countryCode;
    private String _countryName;
    
    public HostModel(){
        this(null,null,null,new LinkedList<String>());
    }
    
    public HostModel(String IPAddress){
        this(IPAddress,null,null,new LinkedList<String>());
    }
    
    public HostModel(String IPAddress, String countryCode, String countryName){
        this(IPAddress,countryCode,countryName,new LinkedList<String>());
    }
    
    public HostModel(String IPAddress, String countryCode, String countryName, List<String> discoveredVHosts){
        _IPAddress = IPAddress;
        _countryCode = countryCode;
        _countryName = countryName;
        _discoveredVHosts = discoveredVHosts;        
    }

    public String getIPAddress() {
        return _IPAddress;
    }

    public void setIPAddress(String IPAddress) {
        _IPAddress = IPAddress;
    }

    public List<String> getDiscoveredVHosts() {
        return _discoveredVHosts;
    }

    public void setDiscoveredVHosts(List<String> discoveredVHosts) {
        _discoveredVHosts = discoveredVHosts;
    }
    
    public void addVHost(String vHost){
        _discoveredVHosts.add(vHost);
    }

    public String getCountryCode() {
        return _countryCode;
    }

    public void setCountryCode(String countryCode) {
        _countryCode = countryCode;
    }

    public String getCountryName() {
        return _countryName;
    }

    public void setCountryName(String countryName) {
        _countryName = countryName;
    }
    
}
