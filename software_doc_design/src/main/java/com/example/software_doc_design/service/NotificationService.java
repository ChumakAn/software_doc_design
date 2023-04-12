package com.example.software_doc_design.service;

import com.example.software_doc_design.domain.Notification;
import com.example.software_doc_design.enums.NotificationType;
import com.example.software_doc_design.mappers.NotificationMapper;
import com.example.software_doc_design.persistance.entity.NotificationEntity;
import com.example.software_doc_design.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final NotificationMapper notificationMapper;
    private final HubService hubService;
    private final DeviceService deviceService;

    public NotificationService(
            NotificationRepository notificationRepository,
            NotificationMapper notificationMapper,
            HubService hubService,
            DeviceService deviceService
    ) {
        this.notificationRepository = notificationRepository;
        this.notificationMapper = notificationMapper;
        this.hubService = hubService;
        this.deviceService = deviceService;
    }

    public void sendNotification(boolean isCritical, String message, Long hubId, Long deviceId) {
        save(Notification.builder()
                .message(message)
                .notificationType(getNotificationType(isCritical))
                .hub(hubService.getHubById(hubId))
                .device(deviceService.getDeviceById(deviceId))
                .build());
    }

    public List<Notification> getAll() {
        return notificationRepository.findAll().stream().map(notificationMapper::toDomain).collect(Collectors.toList());
    }

    public Notification getById(Long id) {
        return notificationMapper.toDomain(notificationRepository.getReferenceById(id));
    }

    public List<Notification> getByDeviceId(Long id) {
        return notificationRepository.getByDeviceId(id).stream().map(notificationMapper::toDomain).collect(Collectors.toList());
    }

    public Notification save(Notification notification) {
        NotificationEntity savedNotification = notificationRepository.save(notificationMapper.toEntity(notification));
        return notificationMapper.toDomain(savedNotification);
    }

    private NotificationType getNotificationType(boolean isCritical) {
        return isCritical ? NotificationType.CRITICAL : NotificationType.INFO;
    }
}
