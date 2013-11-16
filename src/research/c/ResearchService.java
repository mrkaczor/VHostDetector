package research.c;

import config.c.ConfigurationService;
import config.m.ResourcesConfiguration;
import server.c.Server;

/**
 *
 * @author mrkaczor
 */
public class ResearchService {

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

    public boolean checkResearchExist() {
        ResourcesConfiguration res_config = ConfigurationService.getInstance().getResourcesConfiguration();
        String command = "tail "+ res_config.getResultsPath() + "/" + res_config.getResearchStateFileName();
        return Server.getInstance().executeCommand(command, false);
    }

    public void startResearch() {
        ResourcesConfiguration res_config = ConfigurationService.getInstance().getResourcesConfiguration();
        System.out.println("COMMAND: "+"echo '" + HostsService.getInstance().getHostsData().getServersCount()+"' > " + res_config.getResultsPath() + "/" + res_config.getResearchStateFileName());
        Server.getInstance().executeCommand("echo '" + HostsService.getInstance().getHostsData().getServersCount()+"' > " + res_config.getResultsPath() + "/" + res_config.getResearchStateFileName());
    }

}
