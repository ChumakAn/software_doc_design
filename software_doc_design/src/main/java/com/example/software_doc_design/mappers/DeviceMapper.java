package com.example.software_doc_design.mappers;

import com.example.software_doc_design.domain.Device;
import com.example.software_doc_design.persistance.entity.DeviceEntity;
import org.springframework.stereotype.Component;

@Component
public class DeviceMapper {
    private final DetailMapper detailMapper;
    private final ProtectionDataMapper protectionDataMapper;
    private final UserMapper userMapper;
    private final HubMapper hubMapper;

    public DeviceMapper(DetailMapper detailMapper, ProtectionDataMapper protectionDataMapper, UserMapper userMapper, HubMapper hubMapper) {
        this.detailMapper = detailMapper;
        this.protectionDataMapper = protectionDataMapper;
        this.userMapper = userMapper;
        this.hubMapper = hubMapper;
    }

    public Device toDomain(DeviceEntity deviceEntity) {
        Device.DeviceBuilder deviceBuilder = Device.builder();
        return deviceBuilder
                .id(deviceEntity.getId())
                .batteryLevel(deviceEntity.getBatteryLevel())
                .details(detailMapper.toDomain(deviceEntity.getDetail()).getDescription())
                .type(deviceEntity.getType())
                .user(userMapper.toDomain(deviceEntity.getUser()))
                .protection(protectionDataMapper.toDomain(deviceEntity.getProtectionType()))
                .hub(hubMapper.toDomain(deviceEntity.getHub()))
                .name(deviceEntity.getName())
                .build();

    }

    public DeviceEntity toEntity(Device device) {
        DeviceEntity.DeviceEntityBuilder deviceEntityBuilder = DeviceEntity.builder();
        return deviceEntityBuilder
                .id(device.getId())
                .batteryLevel(device.getBatteryLevel())
                .detail(detailMapper.toEntity(device.getDetails()))
                .protectionType(protectionDataMapper.toEntity(device.getProtection()))
                .type(device.getType())
                .hub(hubMapper.toEntity(device.getHub()))
                .user(userMapper.toEntity(device.getUser()))
                .name(device.getName())
                .build();
    }
}
