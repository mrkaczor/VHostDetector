package research.c;

import config.c.ConfigurationService;
import config.m.ResourcesConfiguration;
import java.util.Date;
import java.util.List;
import research.m.HostModel;
import research.m.HostsHolder;
import research.m.ResearchData;
import research.m.ResearchState;
import server.c.Server;
import server.m.Console;

/**
 *
 * @author mrkaczor
 */
public class ResearchService {

    private final int _parallelTasks = 10;
    private final int _taskTimeout = 300;

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
    private int crateBashScripts() {
        Server srv = Server.getInstance();
        HostsHolder hosts = HostsService.getInstance().getHostsData();
        String finishScriptFile = ConfigurationService.getInstance().getResourcesConfiguration().getFinishedScriptFile();
        //scripts_dir
        String scriptPath = ConfigurationService.getInstance().getResourcesConfiguration().getResearchPath() + "/" + ConfigurationService.getInstance().getResourcesConfiguration().getScriptsDirectory();
        int scriptsCount = 0;
        
        //TODO delete next line!!!
        //srv.executeCommand("mkdir results");
        if (srv.executeCommand("mkdir " + scriptPath, false)) {
            //Finish script
            srv.executeCommand("echo '" + createFinishScript() + "' > " + scriptPath + "/" + finishScriptFile, false);
            srv.executeCommand("chmod +x " + scriptPath + "/" + finishScriptFile, false);
            
            String command, scriptName;
            int hostsCount = HostsService.getInstance().getHostsData().getServersCount();
            scriptsCount = _parallelTasks;
            if (_parallelTasks > hostsCount) {
                scriptsCount = hostsCount;
                for (int i = 1; i <= scriptsCount; i++) {
                    command = HostsService.getInstance().generateIPLookupCommand(hosts.getHosts().get(i - 1).getIPAddress(), _taskTimeout);
                    scriptName = generateScriptName(i);
                    srv.executeCommand("echo '" + command + "' > " + scriptPath + "/" + scriptName, false);
                    srv.executeCommand("chmod +x " + scriptPath + "/" + scriptName, false);
                }
            } else {
                int hostsPerTask = (int) Math.floor(1.0 * hostsCount / _parallelTasks);
                int hostsInLastTask = hostsCount - (_parallelTasks - 1) * hostsPerTask;
                for (int i = 0; i < scriptsCount - 1; i++) {
                    scriptName = generateScriptName(i + 1);
                    command = "(";
                    for (int j = 0; j < hostsPerTask; j++) {
                        command += HostsService.getInstance().generateIPLookupCommand(hosts.getHosts().get(i * hostsPerTask + j).getIPAddress(), _taskTimeout);
                        if (j != hostsPerTask - 1) {
                            command += "; ";
                        }
                    }
                    command += ")";
                    srv.executeCommand("echo '" + command + "' >> " + scriptPath + "/" + scriptName, false);
                    srv.executeCommand("chmod +x " + scriptPath + "/" + scriptName, false);
                }
                //Last task
                scriptName = generateScriptName(scriptsCount);
                command = "(";
                for (int i = hostsCount - hostsInLastTask + 1; i <= hostsCount; i++) {
                    command += HostsService.getInstance().generateIPLookupCommand(hosts.getHosts().get(i - 1).getIPAddress(), _taskTimeout);
                    if (i != hostsCount) {
                        command += "; ";
                    }
                }
                command += ")";
                srv.executeCommand("echo '" + command + "' >> " + scriptPath + "/" + scriptName, false);
                srv.executeCommand("chmod +x " + scriptPath + "/" + scriptName, false);
            }
        }
        return scriptsCount;
    }

    private String createFinishScript() {
        String completetionFile = ConfigurationService.getInstance().getResourcesConfiguration().getResearchPath() + "/" +ConfigurationService.getInstance().getResourcesConfiguration().getCompletionListFile();
        String ipListFile = ConfigurationService.getInstance().getResourcesConfiguration().getResearchPath() + "/" +ConfigurationService.getInstance().getResourcesConfiguration().getServersListFile();
        String stateFile = ConfigurationService.getInstance().getResourcesConfiguration().getResearchPath() + "/" +ConfigurationService.getInstance().getResourcesConfiguration().getResearchStateFile();
        String script = "#!/bin/bash\n";
        script += "echo \"$1\" >> " + completetionFile + "\n"
                + "LINES=$(wc -l < " + completetionFile + ")\n"
                + "TOTAL=$(head -1 " + ipListFile + ")\n"
                + "if [ \"$LINES\" == \"$TOTAL\" ]\nthen\n"
                + "echo " + ResearchState.FINISHED + " > " + stateFile + "\n"
                + "echo $(date +%s) >> " + stateFile + "\n"
                + "fi";
        return script;
    }

    private String generateScreenBaseName(Date date) {
        String screenName = "vhostdetector";
        if (date != null) {
            screenName += "_" + date.getTime();
        }
        return screenName;
    }

    private String generateScreenName(int screenId) {
        return _researchData.getRelatedScreenBaseName() + "_" + (screenId < 10 ? "0" : "") + screenId;
    }

    private String generateScriptName(int scriptId) {
        return "script_" + (scriptId < 10 ? "0" : "") + scriptId + ".sh";
    }

    private void initializeResearch() {
        ResourcesConfiguration conf = ConfigurationService.getInstance().getResourcesConfiguration();
        String command;

        _researchData = new ResearchData();
        _researchData.setRelatedScreenBaseName(generateScreenBaseName(_researchData.getStartDate()));
        _researchData.setRelatedScreensCount(_parallelTasks);
        _researchData.setServersTotal(HostsService.getInstance().getHostsData().getServersCount());

        //research_dir
        command = "mkdir " + conf.getResearchPath();
        Server.getInstance().executeCommand(command, false);

        //configuration
        command = "echo " + _researchData.getRelatedScreenBaseName() + " > " + conf.getResearchPath() + "/" + conf.getResearchConfigurationFile();
        Server.getInstance().executeCommand(command, false);
        command = "echo " + _researchData.getRelatedScreensCount() + " >> " + conf.getResearchPath() + "/" + conf.getResearchConfigurationFile();
        Server.getInstance().executeCommand(command, false);
        //state
        command = "echo " + _researchData.getCurrentState() + " > " + conf.getResearchPath() + "/" + conf.getResearchStateFile();
        Server.getInstance().executeCommand(command, false);

        //servers_list
        uploadServersList(conf.getResearchPath() + "/" + conf.getServersListFile());
    }

    private Date retrieveDateFromScreenName(String screenName) {
        Date date = null;
        try {
            long milis = Long.parseLong(screenName.substring(screenName.indexOf("_") + 1));
            date = new Date(milis);
        } catch (NumberFormatException ex) {
            System.err.println("Unable to retrieve date from screen name due to exception: " + ex.getMessage());
        }
        return date;
    }

    private void runBashScripts() {
        String scriptPath = ConfigurationService.getInstance().getResourcesConfiguration().getResearchPath() + "/" + ConfigurationService.getInstance().getResourcesConfiguration().getScriptsDirectory();
        String command;
        for (int i = 0; i < _researchData.getRelatedScreensCount(); i++) {
            command = "screen -dmS " + generateScreenName(i + 1) + " ";
            command += scriptPath + "/" + generateScriptName(i + 1);
            Server.getInstance().executeCommand(command, true);
        }
    }

    private void uploadServersList(String path) {
        int count = HostsService.getInstance().getHostsData().getServersCount();
        String command = "echo " + count + " > " + path;
        if (Server.getInstance().executeCommand(command, false)) {
            for (HostModel host : HostsService.getInstance().getHostsData().getHosts()) {
                command = "echo " + host.getIPAddress() + " >> " + path;
                Server.getInstance().executeCommand(command, false);
            }
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Object PUBLIC methods">
    public boolean checkResearchExist() {
        ResourcesConfiguration res_config = ConfigurationService.getInstance().getResourcesConfiguration();
        String command = "head -1 " + res_config.getResearchPath() + "/" + res_config.getResearchConfigurationFile();
        return Server.getInstance().executeCommand(command, false);
    }

    public ResearchData getResearchData() {
        return _researchData;
    }

    public void loadResearchData() {
        if (checkResearchExist()) {
            ResourcesConfiguration res_config = ConfigurationService.getInstance().getResourcesConfiguration();
            String command = "cat " + res_config.getResearchPath() + "/" + res_config.getResearchConfigurationFile();
            if (Server.getInstance().executeCommand(command, false)) {
                List<String> configFile = Server.getInstance().readOutputBuffer();
                _researchData = new ResearchData(configFile.get(0), Integer.parseInt(configFile.get(1)));
                _researchData.setStartDate(retrieveDateFromScreenName(_researchData.getRelatedScreenBaseName()));

                command = "cat " + res_config.getResearchPath() + "/" + res_config.getResearchStateFile();
                if (Server.getInstance().executeCommand(command, false)) {
                    _researchData.setCurrentState(ResearchState.valueOf(Server.getInstance().readOutputBuffer().get(0)));
                }

                command = "head -1 " + res_config.getResearchPath() + "/" + res_config.getServersListFile();
                if (Server.getInstance().executeCommand(command, false)) {
                    _researchData.setServersTotal(Integer.parseInt(Server.getInstance().readOutputBuffer().get(0)));
                }

                command = "cat " + res_config.getResearchPath() + "/" + res_config.getCompletionListFile();
                if (Server.getInstance().executeCommand(command, false)) {
                    _researchData.setServersCompleted(Server.getInstance().readOutputBuffer().size());
                }
            }
        } else {
            _researchData = new ResearchData();
            _researchData.setStartDate(null);
            _researchData.setCurrentState(ResearchState.NOT_STARTED);
        }
        System.out.println(_researchData);
    }

    public void startResearch() {
        if (!checkResearchExist()) {
            if (HostsService.getInstance().loadServersData()) {
                initializeResearch();
                crateBashScripts();
                runBashScripts();
                Server.getInstance().log(Console.SYSTEM, "Rozpoczęto badania na serwerze " + ConfigurationService.getInstance().getServerConfiguration().getHost() + "!", false);
            } else {
                System.out.println("somethings wrong ;(");
                Server.getInstance().log(Console.SYSTEM, "Nie można rozpocząć badań z powodu braku listy serwerów!", true);
            }
        }
    }
    // </editor-fold>

}
