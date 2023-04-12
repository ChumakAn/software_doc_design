package com.example.software_doc_design.events;

import com.example.software_doc_design.service.NotificationService;
import com.example.software_doc_design.service.SoundAlarmService;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class SecurityEventListener {
    private final SoundAlarmService soundAlarmService;
    private final NotificationService notificationService;

    public SecurityEventListener(SoundAlarmService soundAlarmService, NotificationService notificationService) {
        this.soundAlarmService = soundAlarmService;
        this.notificationService = notificationService;
    }

    @EventListener
    public void handleEvent(SecurityEvent securityEvent) {
        log.info("Event received: " + securityEvent);
        if (securityEvent.isCritical()) {
            log.warn("Critical event received: " + securityEvent);
            soundAlarmService.triggerSoundAlarm(securityEvent.getProtectionType(), securityEvent.getHubId(), securityEvent.getTimestamp());
        }
        notificationService.sendNotification(
                securityEvent.isCritical(),
                securityEvent.getMessage(),
                securityEvent.getHubId(),
                securityEvent.getDeviceId()
        );
        log.info("Event handled: " + securityEvent);
    }
}
