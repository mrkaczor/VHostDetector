/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hosts.c;

import config.c.ConfigurationService;
import config.m.ResourcesConfiguration;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Mateusz
 */
public class HostsService {

    private HostsService() {

    }

    public static HostsService getInstance() {
        return HostsServiceHolder.INSTANCE;
    }

    private static class HostsServiceHolder {

        private static final HostsService INSTANCE = new HostsService();
    }

    public static List<String> readIPs(String filePath) throws FileNotFoundException, IOException {
        List<String> result = new LinkedList<>();

        BufferedReader br = new BufferedReader(new FileReader(new File(filePath)));
        String line = br.readLine();
        while (line != null) {
            result.add(line);
            line = br.readLine();
        }

        return result;
    }

    public static String generateIPLookupCommand(String IPAddress, int timeout) {
        ResourcesConfiguration config = ConfigurationService.getInstance().getResourcesConfiguration();
        String geoIP = config.getGeoIPPath();
        String hostmap = config.getHostmapPath();
        String results = config.getResultsPath();

        if (timeout > 0) {
            return String.format("cd %3$s/ "
                    + "&& ./IPtoLocation.pl %1$s > %5$s/%1$s "
                    + "&& cd "
                    + "&& timeout %2$ds ruby %4$s/hostmap.rb -t %1$s >> %5$s/%1$s "
                    + "&& echo %1$s >> %5$s/completed",
                    IPAddress, timeout, geoIP, hostmap, results);
        } else {
            return String.format("cd %2$s/ "
                    + "&& ./IPtoLocation.pl %1$s > %4$s/%1$s "
                    + "&& cd "
                    + "&& ruby %3$s/hostmap.rb -t %1$s >> %4$s/%1$s "
                    + "&& echo %1$s >> %4$s/completed",
                    IPAddress, geoIP, hostmap, results);
        }
    }
}
