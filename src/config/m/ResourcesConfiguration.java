package config.m;

/**
 *
 * @author mrkaczor
 */
public class ResourcesConfiguration implements Cloneable {

    private final String _researchConfigFile = "conf";
    private final String _researchStateFile = "completed";
    private final String _serversListFile = "iplist";
    private final String _scriptsDirectory = "scripts";
    
    private String _hostsListFilePath;
    private String _geoIPPath;
    private String _researchPath;
    private String _hostmapPath;

    public String getHostsListFilePath() {
        return _hostsListFilePath;
    }

    public void setHostsListFilePath(String hostsListFilePath) {
        _hostsListFilePath = hostsListFilePath;
    }

    public String getGeoIPPath() {
        return _geoIPPath;
    }

    public void setGeoIPPath(String geoIPPath) {
        _geoIPPath = geoIPPath;
    }

    public String getResearchPath() {
        return _researchPath;
    }

    public void setResearchPath(String researchPath) {
        _researchPath = researchPath;
    }

    public String getHostmapPath() {
        return _hostmapPath;
    }

    public void setHostmapPath(String hostmapPath) {
        _hostmapPath = hostmapPath;
    }

    public String getResearchConfigurationFile() {
        return _researchConfigFile;
    }

    public String getResearchStateFile() {
        return _researchStateFile;
    }

    public String getServersListFile() {
        return _serversListFile;
    }

    public String getScriptsDirectory() {
        return _scriptsDirectory;
    }

    @Override
    public ResourcesConfiguration clone() {
        ResourcesConfiguration config = new ResourcesConfiguration();
        config.setHostsListFilePath(_hostsListFilePath);
        config.setGeoIPPath(_geoIPPath);
        config.setHostmapPath(_hostmapPath);
        config.setResearchPath(_researchPath);
        return config;
    }
}
