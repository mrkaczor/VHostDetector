package research.c;

import config.c.ConfigurationService;
import config.m.ResourcesConfiguration;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Date;
import java.util.List;
import research.m.HostModel;
import research.m.HostsHolder;
import research.m.ResearchData;
import research.m.ResearchState;
import server.c.Server;
import server.m.Console;

/**
 * Serwis pozwalający na zarządzanie instancjami i przebiegiem badań.
 * @author mrkaczor
 */
public class ResearchService {

    private final int _parallelTasks = 10;
    private final int _taskTimeout = 1000;
    private int _initialDataSource;
    private int _serversCount;

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
        //HostsHolder hosts = HostsService.getInstance().getHostsData();
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
            //int hostsCount = HostsService.getInstance().getHostsData().getServersCount();
            int hostsCount = _serversCount;
            System.out.println("Detected " + hostsCount + " servers data...");
            scriptsCount = _parallelTasks;
            
            //Read hosts
            String filePath = ConfigurationService.getInstance().getResourcesConfiguration().getHostsListFilePath();
            try {
                RandomAccessFile ra = new RandomAccessFile(new File(filePath), "rw");
                String ipAddress;
                
                if (_parallelTasks > hostsCount) {
                    scriptsCount = hostsCount;
                    System.out.println("Generating " + scriptsCount + " scripts...");
                    for (int i = 1; i <= scriptsCount; i++) {
                        ipAddress = ra.readLine();
                        //command = HostsService.getInstance().generateIPLookupCommand(hosts.getHosts().get(i-1), _taskTimeout);
                        command = HostsService.getInstance().generateIPLookupCommand(ipAddress, _taskTimeout);
                        scriptName = generateScriptName(i);
                        srv.executeCommand("echo '" + command + "' > " + scriptPath + "/" + scriptName, true);
                        srv.executeCommand("chmod +x " + scriptPath + "/" + scriptName, false);
                    }
                    System.out.println("DONE!");
                } else {
                    System.out.println("Generating " + scriptsCount + " scripts...");
                    int hostsPerTask = (int) Math.floor(1.0 * hostsCount / _parallelTasks);
                    int hostsInLastTask = hostsCount - (_parallelTasks - 1) * hostsPerTask;
                    for (int i = 0; i < scriptsCount - 1; i++) {
                        ipAddress = ra.readLine();
                        scriptName = generateScriptName(i + 1);
                        command = "(";
                        for (int j = 0; j < hostsPerTask; j++) {
                            //command += HostsService.getInstance().generateIPLookupCommand(hosts.getHosts().get(i * hostsPerTask + j).getIPAddress(), _taskTimeout);
                            command += HostsService.getInstance().generateIPLookupCommand(ipAddress, _taskTimeout);
                            if (j != hostsPerTask - 1) {
                                command += "; ";
                            }
                        }
                        command += ")";
                        srv.executeCommand("echo '" + command + "' >> " + scriptPath + "/" + scriptName, true);
                        srv.executeCommand("chmod +x " + scriptPath + "/" + scriptName, false);
                    }
                    //Last task
                    scriptName = generateScriptName(scriptsCount);
                    command = "(";
                    for (int i = hostsCount - hostsInLastTask + 1; i <= hostsCount; i++) {
                        ipAddress = ra.readLine();
                        //command += HostsService.getInstance().generateIPLookupCommand(hosts.getHosts().get(i - 1).getIPAddress(), _taskTimeout);
                        command += HostsService.getInstance().generateIPLookupCommand(ipAddress, _taskTimeout);
                            if (i != hostsCount) {
                            command += "; ";
                        }
                    }
                    command += ")";
                    srv.executeCommand("echo '" + command + "' >> " + scriptPath + "/" + scriptName, true);
                    srv.executeCommand("chmod +x " + scriptPath + "/" + scriptName, false);
                    System.out.println("DONE!");
                }
                
                ra.close();
            } catch(IOException ex) {
                Server.getInstance().log(Console.SYSTEM, "Wystąpił błąd podczas próby wczytania danych serwerów: "+ex.getMessage(), true);
                System.err.println("Wystąpił błąd podczas próby wczytania danych serwerów:\n"+ex.getMessage());
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
                + "echo $(date +%s%N | cut -b1-13) >> " + stateFile + "\n"
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
        String filePath = ConfigurationService.getInstance().getResourcesConfiguration().getHostsListFilePath();
        try {
            RandomAccessFile ra = new RandomAccessFile(new File(filePath), "rw");
            String line;
            //Obliczanie ilości adresów IP
            _serversCount = 0;
            while (ra.readLine() != null) {
                _serversCount++;
            }
            ra.seek(0);
            //
            String command = "echo " + _serversCount + " > " + path;
            if (Server.getInstance().executeCommand(command, true)) {
                
                while ((line = ra.readLine()) != null) {
                    command = "echo " + line + " >> " + path;
                    Server.getInstance().executeCommand(command, false);
                }
                Server.getInstance().log(Console.SYSTEM, "Pomyślnie wczytano dane "+_serversCount+" serwerów!", false);
            }
            ra.close();
        } catch(IOException ex) {
            Server.getInstance().log(Console.SYSTEM, "Wystąpił błąd podczas próby zuploadowania danych serwerów: "+ex.getMessage(), true);
            System.err.println("Wystąpił błąd podczas próby zuploadowania danych serwerów:\n"+ex.getMessage());
        }
    }

    private boolean validateResearchData() {
        String filePath = ConfigurationService.getInstance().getResourcesConfiguration().getHostsListFilePath();
        if(filePath != null && !filePath.equals("")) {
            return true;
        }
        return false;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Object PUBLIC methods">
    /**
     * Sprawdza, czy na zdalnym serwerze istnieje instancja badań.
     * @return true, jeżeli na serwerze istnieje instancja badań, false w przeciwnym wypadku
     */
    public boolean checkResearchExist() {
        ResourcesConfiguration res_config = ConfigurationService.getInstance().getResourcesConfiguration();
        String command = "head -1 " + res_config.getResearchPath() + "/" + res_config.getResearchConfigurationFile();
        return Server.getInstance().executeCommand(command, false);
    }

    /**
     * Zwraca dane aktualnie wczytanej instancji badań.
     * @return dane aktualnej instancji badań
     */
    public ResearchData getResearchData() {
        return _researchData;
    }

    /**
     * Pobiera z serwera dane dotyczące znajdującej się na nim instancji badań. Jeżeli na serwerze
     * nie ma żadnej instancji badań, dotychczas wczytana w aplikacji instancja nie ulega zmianie.
     */
    public void loadResearchData() {
        if (checkResearchExist()) {
            ResourcesConfiguration res_config = ConfigurationService.getInstance().getResourcesConfiguration();
            String command = "cat " + res_config.getResearchPath() + "/" + res_config.getResearchConfigurationFile();
            if (Server.getInstance().executeCommand(command, false)) {
                List<String> dataFile = Server.getInstance().readOutputBuffer();
                _researchData = new ResearchData(dataFile.get(0), Integer.parseInt(dataFile.get(1)));
                _researchData.setStartDate(retrieveDateFromScreenName(_researchData.getRelatedScreenBaseName()));

                command = "cat " + res_config.getResearchPath() + "/" + res_config.getResearchStateFile();
                if (Server.getInstance().executeCommand(command, false)) {
                    dataFile = Server.getInstance().readOutputBuffer();
                    _researchData.setCurrentState(ResearchState.valueOf(dataFile.get(0)));
                    if(_researchData.getCurrentState().equals(ResearchState.FINISHED) || _researchData.getCurrentState().equals(ResearchState.TERMINATED)) {
                        _researchData.setEndDate(new Date(Long.parseLong(dataFile.get(1))));
                    }
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
        //System.out.println(_researchData);
    }

    /**
     * Inicjalizuje a następnie rozpoczyna badania na serwerze.
     */
    public void startResearch() {
        if (!checkResearchExist()) {
            if (validateResearchData()) {
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

    /**
     * Powoduje anulowanie aktualnie uruchomionych na serwerze badań.
     */
    public void terminateResearch() {
        if (checkResearchExist()) {
            loadResearchData();
            if(_researchData.getCurrentState().equals(ResearchState.STARTED)) {
                String stateFile = ConfigurationService.getInstance().getResourcesConfiguration().getResearchPath() + "/" +ConfigurationService.getInstance().getResourcesConfiguration().getResearchStateFile();
                //Terminate screen sessions
                for(int i=0; i<_researchData.getRelatedScreensCount(); i++) {
                    Server.getInstance().executeCommand("screen -S " + generateScreenName(i+1) + " -X quit", true);
                }
                //Update state
                _researchData.setCurrentState(ResearchState.TERMINATED);
                _researchData.setEndDate(new Date());
                Server.getInstance().executeCommand("echo '" + _researchData.getCurrentState() + "' > " + stateFile, false);
                Server.getInstance().executeCommand("echo '" + _researchData.getEndDate().getTime() + "' >> " + stateFile, false);
            }
        }
    }
    
    public HostsHolder gatherResearchResults(){
        HostsHolder result = new HostsHolder();
        
        ResourcesConfiguration config = ConfigurationService.getInstance().getResourcesConfiguration();
        String results = config.getResearchPath();
        String completed = config.getCompletionListFile();
        
        String readHostList = "cat " + results + "/" + completed;
        Server.getInstance().executeCommand(readHostList, false);
        List<String> hostsList = Server.getInstance().readOutputBuffer();
        
        for(String host: hostsList){
            HostModel hostModel = new HostModel(host);
            
            String readHostResults = "cat " + results + "/" + host;
            Server.getInstance().executeCommand(readHostResults, false);
            List<String> hostResults = Server.getInstance().readOutputBuffer();
            
            hostModel.setCountryCode(hostResults.get(0));
            hostModel.setCountryName(hostResults.get(1));
            for(String resultLine: hostResults){
                if(resultLine.contains("Found new")){
                    hostModel.addVHost(resultLine.substring(resultLine.lastIndexOf(" ")));
                }
            }
            result.addHost(hostModel);
        }
        
        return result;
    }
    // </editor-fold>

}
