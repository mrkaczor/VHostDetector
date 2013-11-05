/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hosts.c;

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
        if (timeout > 0) {
            return String.format("cd ~/GeoIP/ && ./IPtoLocation.pl %1$s > ~/results/%1$s && cd && timeout %2$ds ruby ~/hostmap/hostmap.rb -t %1$s >> ~/results/%1$s && echo %1$s >> ~/results/completed", IPAddress, timeout);
        } else {
            return String.format("cd ~/GeoIP/ && ./IPtoLocation.pl %1$s > ~/results/%1$s && cd && ruby ~/hostmap/hostmap.rb -t %1$s >> ~/results/%1$s && echo %1$s >> ~/results/completed", IPAddress);
        }
    }
}
