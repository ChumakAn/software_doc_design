package com.example.software_doc_design.mappers;

import com.example.software_doc_design.domain.DeviceProtectionData;
import com.example.software_doc_design.domain.FireProtectionData;
import com.example.software_doc_design.domain.GlassProtectionData;
import com.example.software_doc_design.domain.LeaksProtectionData;
import com.example.software_doc_design.domain.MotionProtectionData;
import com.example.software_doc_design.enums.ProtectionType;
import com.example.software_doc_design.persistance.entity.DeviceProtectionDataEntity;
import org.springframework.stereotype.Component;


@Component
public class ProtectionDataMapper {
    public DeviceProtectionData toDomain(DeviceProtectionDataEntity deviceProtectionDataEntity) {
        switch (deviceProtectionDataEntity.getProtectionType()) {
            case LEAKS_PROTECTION -> {
                LeaksProtectionData.LeaksProtectionDataBuilder leaksProtectionBuilder = LeaksProtectionData.builder();
                return leaksProtectionBuilder
                        .protectionType(ProtectionType.LEAKS_PROTECTION)
                        .waterLevel(deviceProtectionDataEntity.getWaterLevel() == null ? 0 : deviceProtectionDataEntity.getWaterLevel())
                        .deviceState(deviceProtectionDataEntity.getDeviceState())
                        .name(deviceProtectionDataEntity.getName())
                        .build();
            }
            case FIRE_PROTECTION -> {
                FireProtectionData.FireProtectionDataBuilder fireProtectionBuilder = FireProtectionData.builder();
                return fireProtectionBuilder
                        .protectionType(ProtectionType.FIRE_PROTECTION)
                        .deviceState(deviceProtectionDataEntity.getDeviceState())
                        .levelThreshold(deviceProtectionDataEntity.getLevelThreshold() == null ? 0 : deviceProtectionDataEntity.getLevelThreshold())
                        .name(deviceProtectionDataEntity.getName())
                        .build();
            }
            case GLASS_PROTECTION -> {
                GlassProtectionData.GlassProtectionDataBuilder glassProtectionBuilder = GlassProtectionData.builder();
                return glassProtectionBuilder
                        .protectionType(ProtectionType.GLASS_PROTECTION)
                        .deviceState(deviceProtectionDataEntity.getDeviceState())
                        .soundLevel(deviceProtectionDataEntity.getSoundLevel() == null ? 0 : deviceProtectionDataEntity.getSoundLevel())
                        .name(deviceProtectionDataEntity.getName())
                        .build();
            }
            case MOTION_PROTECTION -> {
                MotionProtectionData.MotionProtectionDataBuilder motionProtectionBuilder = MotionProtectionData.builder();
                return motionProtectionBuilder
                        .protectionType(ProtectionType.MOTION_PROTECTION)
                        .movementCount(deviceProtectionDataEntity.getMovementCount() == null ? 0 : deviceProtectionDataEntity.getMovementCount())
                        .deviceState(deviceProtectionDataEntity.getDeviceState())
                        .name(deviceProtectionDataEntity.getName())
                        .build();
            }
            default -> throw new IllegalArgumentException("Unknown protection type");
        }
    }

    public <T extends DeviceProtectionData> DeviceProtectionDataEntity toEntity(DeviceProtectionData deviceProtection) {
        DeviceProtectionDataEntity.DeviceProtectionDataEntityBuilder deviceProtectionTypeEntityBuilder = DeviceProtectionDataEntity.builder();
        return switch (deviceProtection.getProtectionType()) {
            case MOTION_PROTECTION -> deviceProtectionTypeEntityBuilder
                    .protectionType(deviceProtection.getProtectionType())
                    .name(deviceProtection.getName())
                    .deviceState(deviceProtection.getDeviceState())
                    .movementCount(((MotionProtectionData) deviceProtection).getMovementCount())
                    .build();
            case GLASS_PROTECTION -> deviceProtectionTypeEntityBuilder
                    .protectionType(deviceProtection.getProtectionType())
                    .name(deviceProtection.getName())
                    .deviceState(deviceProtection.getDeviceState())
                    .soundLevel(((GlassProtectionData) deviceProtection).getSoundLevel())
                    .build();
            case FIRE_PROTECTION -> deviceProtectionTypeEntityBuilder
                    .protectionType(deviceProtection.getProtectionType())
                    .name(deviceProtection.getName())
                    .deviceState(deviceProtection.getDeviceState())
                    .levelThreshold(((FireProtectionData) deviceProtection).getLevelThreshold())
                    .build();
            case LEAKS_PROTECTION -> deviceProtectionTypeEntityBuilder
                    .protectionType(deviceProtection.getProtectionType())
                    .name(deviceProtection.getName())
                    .deviceState(deviceProtection.getDeviceState())
                    .waterLevel(((LeaksProtectionData) deviceProtection).getWaterLevel())
                    .build();
        };
    }
}
