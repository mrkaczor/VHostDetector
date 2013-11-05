package config.c;

import config.m.ServerConfiguration;

/**
 *
 * @author mrkaczor
 */
public class ConfigurationService {
    private ServerConfiguration _ServerConfiguration;
    
    public static ConfigurationService getInstance() {
        return ConfigurationServiceHolder.INSTANCE;
    }
    
    private static class ConfigurationServiceHolder {
        private static final ConfigurationService INSTANCE = new ConfigurationService();
    }
    
    private ConfigurationService() {
        _ServerConfiguration = new ServerConfiguration();
    }
    
    public ServerConfiguration getServerConfiguration() {
        return _ServerConfiguration;
    }
    
    public void updateServerConfiguration(ServerConfiguration configuration) {
        _ServerConfiguration = configuration;
    }
    
}
