package config.c;

import config.m.ResourcesConfiguration;
import config.m.ServerConfiguration;

/**
 *
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

    public ResourcesConfiguration getResourcesConfiguration() {
        return _ResourcesConfiguration;
    }
    
    public ServerConfiguration getServerConfiguration() {
        return _ServerConfiguration;
    }

    public void updateResourcesConfiguration(ResourcesConfiguration _ResourcesConfiguration) {
        this._ResourcesConfiguration = _ResourcesConfiguration;
    }
    
    public void updateServerConfiguration(ServerConfiguration configuration) {
        _ServerConfiguration = configuration;
    }
    
}
