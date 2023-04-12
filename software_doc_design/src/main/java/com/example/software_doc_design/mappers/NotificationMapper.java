package com.example.software_doc_design.mappers;

import com.example.software_doc_design.domain.Notification;
import com.example.software_doc_design.persistance.entity.NotificationEntity;
import org.springframework.stereotype.Component;

@Component
public class NotificationMapper {
    private final HubMapper hubMapper;
    private final DeviceMapper deviceMapper;

    public NotificationMapper(HubMapper hubMapper, DeviceMapper deviceMapper) {
        this.hubMapper = hubMapper;
        this.deviceMapper = deviceMapper;
    }


    public Notification toDomain(NotificationEntity notificationEntity) {
        Notification.NotificationBuilder notificationBuilder = Notification.builder();
        return notificationBuilder
                .message(notificationEntity.getMessage())
                .notificationType(notificationEntity.getNotificationType())
                .hub(hubMapper.toDomain(notificationEntity.getHub()))
                .device(deviceMapper.toDomain(notificationEntity.getDevice()))
                .build();
    }

    public NotificationEntity toEntity(Notification notification) {
        NotificationEntity.NotificationEntityBuilder notificationEntityBuilder = NotificationEntity.builder();
        return notificationEntityBuilder
                .message(notification.getMessage())
                .notificationType(notification.getNotificationType())
                .hub(hubMapper.toEntity(notification.getHub()))
                .device(deviceMapper.toEntity(notification.getDevice()))
                .build();
    }
}
