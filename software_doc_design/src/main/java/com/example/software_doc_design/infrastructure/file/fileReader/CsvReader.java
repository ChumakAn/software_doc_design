package com.example.software_doc_design.infrastructure.file.fileReader;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

@Component
public class CsvReader implements IFileReader {
    private static final String USER_HEADER = "id,name,surname,login,password,phoneNumber";
    private static final String DESCRIPTION_HEADER = "id,description";
    private static final String PROTECTION_HEADER = "id," +
            "device_state," +
            "installation_method," +
            "level_threshold," +
            "movement_count," +
            "name," +
            "type," +
            "sound_level," +
            "water_level";
    private static final String HUB_HEADER = "id,event_log,type,user_id";
    private static final String SOUND_ALARM_HEADER = "id,timestamp,type,hub_id";
    private static final String NOTIFICATION_HEADER = "id,message,type,device_id,hub_id";
    private static final String DEVICE_HEADER = "id,battery_level,name,type,detail_id,hub_id,protection_type_id,user_id";

    @Override
    public String readFile(String path) {
        return null;
    }

    public Map<String, List<String>> readCsvFile(String path) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(path));
        sc.useDelimiter("\n");
        Map<String, List<String>> map = new HashMap<>();
        String header = "";
        while (sc.hasNext()) {
            String next = sc.next();
            if (next.equals(USER_HEADER)
                    || next.equals(DESCRIPTION_HEADER)
                    || next.equals(PROTECTION_HEADER)
                    || next.equals(HUB_HEADER)
                    || next.equals(SOUND_ALARM_HEADER)
                    || next.equals(NOTIFICATION_HEADER)
                    || next.equals(DEVICE_HEADER)) {

                header = next;
                map.put(header, new ArrayList<>());
            } else {
                map.get(header).add(next);
            }

        }
        sc.close();
        return map;
    }
}
