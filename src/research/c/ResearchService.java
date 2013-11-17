package research.c;

import config.c.ConfigurationService;
import config.m.ResourcesConfiguration;
import java.util.Calendar;
import research.m.HostModel;
import server.c.Server;

/**
 *
 * @author mrkaczor
 */
public class ResearchService {

    private String _remoteScreenName;

    // <editor-fold defaultstate="collapsed" desc="Creating object">
    // <editor-fold defaultstate="collapsed" desc="Singleton">
    public static ResearchService getInstance() {
        return ResearchServiceHolder.INSTANCE;
    }

    private static class ResearchServiceHolder {
        private static final ResearchService INSTANCE = new ResearchService();
    }
    // </editor-fold>
    
    private ResearchService() {
        
    }
    // </editor-fold>

    private String generateScreenName() {
        String screenName = "vhostdetector";
        screenName += "_"+Calendar.getInstance().getTime().getTime();
        return screenName;
    }

    private void initializeResearchOnServer() {
        ResourcesConfiguration conf = ConfigurationService.getInstance().getResourcesConfiguration();
        _remoteScreenName = generateScreenName();
        String command;
        
        //research_dir
        command = "mkdir" + conf.getResearchPath();
        Server.getInstance().executeCommand(command, false);
        
        //configuration
        command = "echo " + _remoteScreenName + " > " + conf.getResearchPath() + "/" + conf.getResearchConfigurationFile();
        Server.getInstance().executeCommand(command, false);
        
        //servers_list
        uploadServersList(conf.getResearchPath() + "/" + conf.getServersListFile());
    }

    private void uploadServersList(String path) {
        int count = HostsService.getInstance().getHostsData().getServersCount();
        String command = "echo " + count + " > " + path;
        if(Server.getInstance().executeCommand(command, false)) {
            for(HostModel host : HostsService.getInstance().getHostsData().getHosts()) {
                command = "echo " + host.getIPAddress() + " >> " + path;
                Server.getInstance().executeCommand(command, false);
            }
        }
    }

    public boolean checkResearchExist() {
        ResourcesConfiguration res_config = ConfigurationService.getInstance().getResourcesConfiguration();
        String command = "cat "+ res_config.getResearchPath() + "/" + res_config.getResearchConfigurationFile();
        return Server.getInstance().executeCommand(command, false);
    }

    public void startResearch() {
        initializeResearchOnServer();
        //TODO next steps...
    }

}
