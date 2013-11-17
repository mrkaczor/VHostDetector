package research.c;

import config.c.ConfigurationService;
import config.m.ResourcesConfiguration;
import java.util.Date;
import java.util.List;
import research.m.HostModel;
import research.m.ResearchData;
import server.c.Server;

/**
 *
 * @author mrkaczor
 */
public class ResearchService {

    private ResearchData _researchData;

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

    // <editor-fold defaultstate="collapsed" desc="Object PRIVATE methods">
    private String generateScreenName(Date date) {
        String screenName = "vhostdetector";
        if(date != null) {
            screenName += "_"+date.getTime();
        }
        return screenName;
    }

    private void initializeResearch() {
        ResourcesConfiguration conf = ConfigurationService.getInstance().getResourcesConfiguration();
        String command;
        
        _researchData = new ResearchData();
        _researchData.setRelatedScreenName(generateScreenName(_researchData.getStartDate()));
        _researchData.setServersTotal(HostsService.getInstance().getHostsData().getServersCount());
        
        //research_dir
        command = "mkdir" + conf.getResearchPath();
        Server.getInstance().executeCommand(command, false);
        
        //configuration
        command = "echo " + _researchData.getRelatedScreenName() + " > " + conf.getResearchPath() + "/" + conf.getResearchConfigurationFile();
        Server.getInstance().executeCommand(command, false);
        
        //servers_list
        uploadServersList(conf.getResearchPath() + "/" + conf.getServersListFile());
    }

    private Date retrieveDateFromScreenName(String screenName) {
        Date date = null;
        try {
            long milis = Long.parseLong(screenName.substring(screenName.indexOf("_")+1));
            date = new Date(milis);
        } catch (NumberFormatException ex) {
            System.err.println("Unable to retrieve date from screen name due to exception: "+ex.getMessage());
        }
        return date;
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
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Object PUBLIC methods">
    public boolean checkResearchExist() {
        ResourcesConfiguration res_config = ConfigurationService.getInstance().getResourcesConfiguration();
        String command = "head -1 "+ res_config.getResearchPath() + "/" + res_config.getResearchConfigurationFile();
        return Server.getInstance().executeCommand(command, false);
    }

    public void loadResearchData() {
        if(checkResearchExist()) {
            ResourcesConfiguration res_config = ConfigurationService.getInstance().getResourcesConfiguration();
            String command = "cat "+ res_config.getResearchPath() + "/" + res_config.getResearchConfigurationFile();
            if(Server.getInstance().executeCommand(command, false)) {
                List<String> configFile = Server.getInstance().readOutputBuffer();
                _researchData = new ResearchData(configFile.get(0));
                _researchData.setStartDate(retrieveDateFromScreenName(_researchData.getRelatedScreenName()));
                
                command = "head -1 "+ res_config.getResearchPath() + "/" + res_config.getServersListFile();
                if(Server.getInstance().executeCommand(command, false)) {
                    _researchData.setServersTotal(Integer.parseInt(Server.getInstance().readOutputBuffer().get(0)));
                }
                
                command = "cat "+ res_config.getResearchPath() + "/" + res_config.getResearchStateFile();
                if(Server.getInstance().executeCommand(command, false)) {
                    _researchData.setServersTotal(Server.getInstance().readOutputBuffer().size());
                }
            }
        }
        System.out.println(_researchData);
    }

    public void startResearch() {
        if(!checkResearchExist()) {
            initializeResearch();
            //TODO next steps...
        }
    }
    // </editor-fold>

}
