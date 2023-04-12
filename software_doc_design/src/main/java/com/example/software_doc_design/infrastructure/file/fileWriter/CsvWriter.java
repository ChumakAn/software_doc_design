package com.example.software_doc_design.infrastructure.file.fileWriter;

import com.example.software_doc_design.enums.DeviceState;
import com.example.software_doc_design.enums.DeviceType;
import com.example.software_doc_design.enums.HubType;
import com.example.software_doc_design.enums.NotificationType;
import com.example.software_doc_design.enums.ProtectionType;
import com.example.software_doc_design.enums.SoundAlarmType;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.RandomStringUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Random;

@Log4j2
public class CsvWriter implements IFileWriter {

    private static final String FILE_NAME = "database.csv";
    private static final String USER_HEADER = "id,name,surname,login,password,phoneNumber";
    private static final String DESCRIPTION_HEADER = "id,description";
    private static final String PROTECTION_HEADER ="id," +
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
    private static final String DEVICE_HEADER = "id,battery_level,name,type,detail_id,hub_id,protection_type_id,user_id";
    private static final String NOTIFICATION_HEADER = "id,message,type,device_id,hub_id";

    private final Random random = new Random();

    @Override
    public void write(String content) {
        //TODO
    }

    public void writeDatabaseData() throws IOException {
        File csvFile = new File(FILE_NAME);
        if (!csvFile.exists()) {
            csvFile.createNewFile();
            csvFile = new File(FILE_NAME);
        }
        try (FileWriter fileWriter = new FileWriter(csvFile)) {
            StringBuilder stringBuilder = new StringBuilder();
            writeUserData(stringBuilder);
            writeDescriptionData(stringBuilder);
            writeProtectionData(stringBuilder);
            writeHubData(stringBuilder);
            writeSoundAlarmData(stringBuilder);
            writeDeviceData(stringBuilder);
            writeNotificationData(stringBuilder);
            fileWriter.write(stringBuilder.toString());
            fileWriter.flush();
        } catch (IOException e) {
           log.warn("Error while writing to file", e);
        }
    }

    private void writeUserData(StringBuilder stringBuilder) {
        stringBuilder.append(USER_HEADER);
        stringBuilder.append("\n");
        for (int i = 1; i < 200; i++) {
            stringBuilder.append(i);
            stringBuilder.append(",");
            stringBuilder.append(RandomStringUtils.randomAlphabetic(5));
            stringBuilder.append(",");
            stringBuilder.append(RandomStringUtils.randomAlphabetic(5));
            stringBuilder.append(",");
            stringBuilder.append(RandomStringUtils.randomAlphabetic(5));
            stringBuilder.append(",");
            stringBuilder.append(RandomStringUtils.randomAlphabetic(5));
            stringBuilder.append(",");
            stringBuilder.append(RandomStringUtils.randomAlphabetic(10));
            stringBuilder.append("\n");
        }
    }

    private void writeDescriptionData(StringBuilder stringBuilder) {
        stringBuilder.append(DESCRIPTION_HEADER);
        stringBuilder.append("\n");
        for (int i = 1; i < 200; i++) {
            stringBuilder.append(i);
            stringBuilder.append(",");
            stringBuilder.append(RandomStringUtils.randomAlphabetic(20));
            stringBuilder.append("\n");
        }
    }

    private void writeHubData(StringBuilder stringBuilder) {
        HubType[] hubTypes = HubType.values();
        stringBuilder.append(HUB_HEADER);
        stringBuilder.append("\n");
        for (int i = 1; i < 200; i++) {
            stringBuilder.append(i);
            stringBuilder.append(",");
            stringBuilder.append(RandomStringUtils.randomAlphabetic(20));
            stringBuilder.append(",");
            stringBuilder.append(hubTypes[random.nextInt(hubTypes.length)]);
            stringBuilder.append(",");
            stringBuilder.append(i == 1 ? 1 : random.nextInt(1, i));
            stringBuilder.append("\n");
        }
    }

    private void writeProtectionData(StringBuilder stringBuilder) {
        DeviceState[] deviceStates = DeviceState.values();
        ProtectionType[] protectionTypes = ProtectionType.values();
        stringBuilder.append(PROTECTION_HEADER);
        stringBuilder.append("\n");
        for (int i = 1; i < 200; i++) {
            stringBuilder.append(i);
            stringBuilder.append(",");
            stringBuilder.append(deviceStates[random.nextInt(deviceStates.length)]);
            stringBuilder.append(",");
            stringBuilder.append(RandomStringUtils.randomAlphabetic(10));
            stringBuilder.append(",");
            stringBuilder.append(random.nextInt(100));
            stringBuilder.append(",");
            stringBuilder.append(random.nextInt(100));
            stringBuilder.append(",");
            stringBuilder.append(RandomStringUtils.randomAlphabetic(10));
            stringBuilder.append(",");
            stringBuilder.append(protectionTypes[random.nextInt(protectionTypes.length)]);
            stringBuilder.append(",");
            stringBuilder.append(random.nextInt(100));
            stringBuilder.append(",");
            stringBuilder.append(random.nextInt(100));
            stringBuilder.append("\n");
        }
    }

    private void writeSoundAlarmData(StringBuilder stringBuilder) {
        stringBuilder.append(SOUND_ALARM_HEADER);
        stringBuilder.append("\n");
        SoundAlarmType[] soundAlarmTypes = SoundAlarmType.values();
        for (int i = 1; i < 200; i++) {
            stringBuilder.append(i);
            stringBuilder.append(",");
            stringBuilder.append(Instant.now().minus(random.nextInt(100), ChronoUnit.HOURS));
            stringBuilder.append(",");
            stringBuilder.append(soundAlarmTypes[random.nextInt(soundAlarmTypes.length)]);
            stringBuilder.append(",");
            stringBuilder.append(i == 1 ? 1 : random.nextInt(1, i));
            stringBuilder.append("\n");
        }
    }

    private void writeDeviceData(StringBuilder stringBuilder) {
        stringBuilder.append(DEVICE_HEADER);
        stringBuilder.append("\n");
        DeviceType[] deviceTypes = DeviceType.values();
        for (int i = 1; i < 200; i++) {
            stringBuilder.append(i);
            stringBuilder.append(",");
            stringBuilder.append(random.nextInt(100));
            stringBuilder.append(",");
            stringBuilder.append(RandomStringUtils.randomAlphabetic(10));
            stringBuilder.append(",");
            stringBuilder.append(deviceTypes[random.nextInt(deviceTypes.length)]);
            stringBuilder.append(",");
            stringBuilder.append(i == 1 ? 1 : random.nextInt(1, i));
            stringBuilder.append(",");
            stringBuilder.append(i == 1 ? 1 : random.nextInt(1, i));
            stringBuilder.append(",");
            stringBuilder.append(i == 1 ? 1 : random.nextInt(1, i));
            stringBuilder.append(",");
            stringBuilder.append(i == 1 ? 1 : random.nextInt(1, i));
            stringBuilder.append("\n");
        }
    }

    private void writeNotificationData(StringBuilder stringBuilder) {
        stringBuilder.append(NOTIFICATION_HEADER);
        stringBuilder.append("\n");
        NotificationType[] notificationTypes = NotificationType.values();
        for (int i = 1; i < 200; i++) {
            stringBuilder.append(i);
            stringBuilder.append(",");
            stringBuilder.append(RandomStringUtils.randomAlphabetic(20));
            stringBuilder.append(",");
            stringBuilder.append(notificationTypes[random.nextInt(notificationTypes.length)]);
            stringBuilder.append(",");
            stringBuilder.append(i == 1 ? 1 : random.nextInt(1, i));
            stringBuilder.append(",");
            stringBuilder.append(i == 1 ? 1 : random.nextInt(1, i));
            stringBuilder.append("\n");
        }
    }

    public static void main(String[] args) {
        CsvWriter csvWriter = new CsvWriter();
        try {
            csvWriter.writeDatabaseData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
