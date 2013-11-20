package config.m;

/**
 * Konfiguracja zasobów zewnętrznych.
 * @author mrkaczor
 */
public class ResourcesConfiguration implements Cloneable {

    private final String _completionListFile = "completed";
    private final String _researchConfigFile = "conf";
    private final String _researchStateFile = "status";
    private final String _serversListFile = "iplist";
    private final String _scriptsDirectory = "scripts";
    private final String _scriptFinishFile = "finish.sh";
    
    private String _hostsListFilePath;
    private String _geoIPPath;
    private String _researchPath;
    private String _hostmapPath;

    /**
     * Zwraca ścieżkę do pliku z listą adresów IP serwerów.
     * @return ścieżka do pliku listy serwerów
     */
    public String getHostsListFilePath() {
        return _hostsListFilePath;
    }

    /**
     * Ustawia ścieżkę do pliku z listą adresów IP serwerów.
     * @param hostsListFilePath ścieżka do pliku listy serwerów
     */
    public void setHostsListFilePath(String hostsListFilePath) {
        _hostsListFilePath = hostsListFilePath;
    }

    /**
     * Zwraca ścieżkę do folderu głównego aplikacji GeoIP na serwerze zdalnym.
     * @return ścieżka do aplikacji GeoIP na serwerze zdalnym
     */
    public String getGeoIPPath() {
        return _geoIPPath;
    }

    /**
     * Ustawia ścieżkę do folderu głównego aplikacji GeoIP na serwerze zdalnym.
     * @param geoIPPath ścieżka do aplikacji GeoIP na serwerze zdalnym
     */
    public void setGeoIPPath(String geoIPPath) {
        _geoIPPath = geoIPPath;
    }

    /**
     * Zwraca ścieżkę do folderu głównego badań na serwerze zdalnym.
     * @return ścieżka do folderu badań na serwerze zdalnym
     */
    public String getResearchPath() {
        return _researchPath;
    }

    /**
     * Ustawia ścieżkę do folderu głównego badań na serwerze zdalnym.
     * @param researchPath ścieżka do folderu badań na serwerze zdalnym
     */
    public void setResearchPath(String researchPath) {
        _researchPath = researchPath;
    }

    /**
     * Zwraca ścieżkę do folderu głównego aplikacji HostMap na serwerze zdalnym.
     * @return ścieżka do aplikacji HostMap na serwerze zdalnym
     */
    public String getHostmapPath() {
        return _hostmapPath;
    }

    /**
     * Ustawia ścieżkę do folderu głównego aplikacji HostMap na serwerze zdalnym.
     * @param hostmapPath ścieżka do aplikacji HostMap na serwerze zdalnym
     */
    public void setHostmapPath(String hostmapPath) {
        _hostmapPath = hostmapPath;
    }

    /**
     * Zwraca nazwę pliku zawierającego adresy IP serwerów, które zostaly pomyślnie przebadane.
     * @return nazwa pliku zawierającego listę przebadanych serwerów
     */
    public String getCompletionListFile() {
        return _completionListFile;
    }

    /**
     * Zwraca nazwę pliku zawierającego konfigurację badań.
     * @return nazwa pliku konfiguracyjnego badań
     */
    public String getResearchConfigurationFile() {
        return _researchConfigFile;
    }

    /**
     * Zwraca nazwę pliku zawierającego aktualny status badań.
     * @return nazwa pliku zawierającego aktualny status badań
     */
    public String getResearchStateFile() {
        return _researchStateFile;
    }

    /**
     * Zwraca nazwę pliku zawierającego listę serwerów do przebadania.
     * @return nazwa pliku zawierającego listę serwerów do przebadania
     */
    public String getServersListFile() {
        return _serversListFile;
    }

    /**
     * Zwraca nazwę folderu w którym przechowywane są skrypty.
     * @return nazwa folderu zawierającego skrypty
     */
    public String getScriptsDirectory() {
        return _scriptsDirectory;
    }

    /**
     * Zwraca nazwę pliku zawierającego skrypt wykonywany po zakończeniu badań dla pojedynczego serwera.
     * @return nazwa pliku ze skryptem finalizującym badania pojedynczego serwera
     */
    public String getFinishedScriptFile() {
        return _scriptFinishFile;
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
