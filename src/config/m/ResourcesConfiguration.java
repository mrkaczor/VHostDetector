package config.m;

/**
 *
 * @author mrkaczor
 */
public class ResourcesConfiguration implements Cloneable {
    
    private final String _localDataDirectory = "download/";
    private final String _researchStateFile = "completed";
    
    private String _hostsListFilePath;
    private String _geoIPPath;
    private String _resultsPath;
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

    public String getResultsPath() {
        return _resultsPath;
    }

    public void setResultsPath(String resultsPath) {
        _resultsPath = resultsPath;
    }

    public String getHostmapPath() {
        return _hostmapPath;
    }

    public void setHostmapPath(String hostmapPath) {
        _hostmapPath = hostmapPath;
    }

    public String getLocalDataDirectory() {
        return _localDataDirectory;
    }

    public String getResearchStateFileName() {
        return _researchStateFile;
    }

    @Override
    public ResourcesConfiguration clone() {
        ResourcesConfiguration config = new ResourcesConfiguration();
        config.setHostsListFilePath(_hostsListFilePath);
        config.setGeoIPPath(_geoIPPath);
        config.setHostmapPath(_hostmapPath);
        config.setResultsPath(_resultsPath);
        return config;
    }
}
