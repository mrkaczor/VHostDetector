package config.c;

import config.m.ResourcesConfiguration;
import config.m.ServerConfiguration;

/**
 * Serwis umożliwiający zarządzanie poszczególnymi konfiguracjami w aplikacji.
 * @author mrkaczor
 */
public class ConfigurationService {
    private ServerConfiguration _ServerConfiguration;
    private ResourcesConfiguration _ResourcesConfiguration;
    
    public static ConfigurationService getInstance() {
        return ConfigurationServiceHolder.INSTANCE;
    }
    
    private static class ConfigurationServiceHolder {
        private static final ConfigurationService INSTANCE = new ConfigurationService();
    }
    
    private ConfigurationService() {
        _ServerConfiguration = new ServerConfiguration();
        _ResourcesConfiguration = new ResourcesConfiguration();
    }

    /**
     * Zwraca konfigurację zasobów zewnętrznych.
     * @return konfiguracja zasobów
     */
    public ResourcesConfiguration getResourcesConfiguration() {
        return _ResourcesConfiguration;
    }

    /**
     * Zwraca konfigurację połączenia z serwerem.
     * @return konfiguracja połączenia z serwerem
     */
    public ServerConfiguration getServerConfiguration() {
        return _ServerConfiguration;
    }

    /**
     * Ustawia nową konfigurację zasobów zewnętrznych.
     * @param configuration nowa konfiguracja zasobów zewnętrznych
     */
    public void updateResourcesConfiguration(ResourcesConfiguration configuration) {
        this._ResourcesConfiguration = configuration;
    }

    /**
     * Ustawia nową konfigurację połączenia z serwerem.
     * @param configuration nowa konfiguracja połączenia z serwerem
     */
    public void updateServerConfiguration(ServerConfiguration configuration) {
        _ServerConfiguration = configuration;
    }
    
}
