package com.example.software_doc_design.service;

import com.example.software_doc_design.domain.Device;
import com.example.software_doc_design.domain.Hub;
import com.example.software_doc_design.domain.Notification;
import com.example.software_doc_design.domain.SoundAlarm;
import com.example.software_doc_design.domain.User;
import com.example.software_doc_design.enums.DeviceState;
import com.example.software_doc_design.enums.DeviceType;
import com.example.software_doc_design.enums.HubType;
import com.example.software_doc_design.enums.NotificationType;
import com.example.software_doc_design.enums.ProtectionType;
import com.example.software_doc_design.enums.SoundAlarmType;
import com.example.software_doc_design.persistance.entity.DeviceProtectionDataEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

@Service
public class DatabasePersistenceService {
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
    private static final String DEVICE_HEADER = "id,battery_level,name,type,detail_id,hub_id,protection_type_id,user_id";
    private static final String NOTIFICATION_HEADER = "id,message,type,device_id,hub_id";
    private final UserService userService;
    private final DetailService detailService;
    private final DeviceProtectionDataService protectionService;
    private final HubService hubService;
    private final SoundAlarmService soundAlarmService;
    private final NotificationService notificationService;
    private final DeviceService deviceService;

    private DatabasePersistenceService(
            UserService userService,
            DetailService detailService,
            DeviceProtectionDataService protectionService,
            HubService hubService,
            SoundAlarmService soundAlarmService,
            NotificationService notificationService,
            DeviceService deviceService
    ) {
        this.userService = userService;
        this.detailService = detailService;
        this.protectionService = protectionService;
        this.hubService = hubService;
        this.soundAlarmService = soundAlarmService;
        this.notificationService = notificationService;
        this.deviceService = deviceService;
    }

    public void persistDatabaseData(Map<String, List<String>> dataFromCsv) {
        readAndSaveUserData(dataFromCsv.get(USER_HEADER));
        readAndSaveDescriptionData(dataFromCsv.get(DESCRIPTION_HEADER));
        readAndSaveProtectionData(dataFromCsv.get(PROTECTION_HEADER));
        readAndSaveHubData(dataFromCsv.get(HUB_HEADER));
        readAndSaveSoundAlarmData(dataFromCsv.get(SOUND_ALARM_HEADER));
        readAndSaveDeviceData(dataFromCsv.get(DEVICE_HEADER));
        readAndSaveNotificationData(dataFromCsv.get(NOTIFICATION_HEADER));
    }

    private void readAndSaveUserData(List<String> list) {
        for (String s : list) {
            String[] split = s.split(",");
            userService.save(User.builder()
                    .name(split[1])
                    .surname(split[2])
                    .login(split[3])
                    .password(split[4])
                    .phoneNumber(split[5])
                    .build()
            );
        }
    }

    private void readAndSaveDescriptionData(List<String> list) {
        for (String s : list) {
            String[] split = s.split(",");
            detailService.save(split[1]);
        }
    }

    private void readAndSaveProtectionData(List<String> list) {
        for (String s : list) {
            String[] split = s.split(",");
            protectionService.save(DeviceProtectionDataEntity.builder()
                    .deviceState(DeviceState.valueOf(split[1]))
                    .installationMethod(split[2])
                    .levelThreshold(Integer.valueOf(split[3]))
                    .movementCount(Integer.valueOf(split[4]))
                    .name(split[5])
                    .protectionType(ProtectionType.valueOf(split[6]))
                    .soundLevel(Integer.valueOf(split[7]))
                    .waterLevel(Integer.valueOf(split[8]))
                    .build());
        }
    }

    private void readAndSaveHubData(List<String> list) {
        for (String s : list) {
            String[] split = s.split(",");
            hubService.save(Hub.builder()
                    .eventLog(split[1])
                    .hubType(HubType.valueOf(split[2]))
                    .user(userService.getById(Long.valueOf(split[3])))
                    .build()
            );
        }
    }

    private void readAndSaveSoundAlarmData(List<String> list) {
        for (String s : list) {
            String[] split = s.split(",");
            soundAlarmService.save(SoundAlarm.builder()
                    .soundAlarmTimestamp(Instant.parse(split[1]))
                    .soundAlarmType(SoundAlarmType.valueOf(split[2]))
                    .hub(hubService.getHubById(Long.valueOf(split[3])))
                    .build()
            );
        }
    }

    private void readAndSaveDeviceData(List<String> list) {
        for (String s : list) {
            String[] split = s.split(",");
            deviceService.save(Device.builder()
                    .batteryLevel(split[1])
                    .name(split[2])
                    .type(DeviceType.valueOf(split[3]))
                    .details(detailService.getById(Long.valueOf(split[4])).getDescription())
                    .hub(hubService.getHubById(Long.valueOf(split[5])))
                    .protection(protectionService.getById(Long.valueOf(split[6])))
                    .user(userService.getById(Long.valueOf(split[7])))
                    .build()
            );
        }
    }

    private void readAndSaveNotificationData(List<String> list) {
        for (String s : list) {
            String[] split = s.split(",");
            notificationService.save(Notification.builder()
                    .message(split[1])
                    .notificationType(NotificationType.valueOf(split[2]))
                    .device(deviceService.getDeviceById(Long.valueOf(split[3])))
                    .hub(hubService.getHubById(Long.valueOf(split[4])))
                    .build()
            );
        }
    }
}
