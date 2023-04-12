package com.example.software_doc_design.service;

import com.example.software_doc_design.domain.Device;
import com.example.software_doc_design.events.EventPublisher;
import com.example.software_doc_design.events.SecurityEvent;
import com.example.software_doc_design.mappers.DeviceMapper;
import com.example.software_doc_design.persistance.entity.DeviceEntity;
import com.example.software_doc_design.repository.DeviceRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeviceService {
    private final DeviceRepository deviceRepository;
    private final DeviceMapper deviceMapper;
    private final HubService hubService;
    private final EventPublisher eventPublisher;

    public DeviceService(DeviceRepository deviceRepository, DeviceMapper deviceMapper, HubService hubService, EventPublisher eventPublisher) {
        this.deviceRepository = deviceRepository;
        this.deviceMapper = deviceMapper;
        this.hubService = hubService;
        this.eventPublisher = eventPublisher;
    }

    public Device save(Device device) {
        DeviceEntity deviceEntity = deviceMapper.toEntity(device);
        deviceRepository.save(deviceEntity);
        return deviceMapper.toDomain(deviceEntity);
    }

    public Device getDeviceById(Long id) {
        if (deviceRepository.existsById(id)) {
            DeviceEntity deviceEntity = deviceRepository.getReferenceById(id);
            return deviceMapper.toDomain(deviceEntity);
        }
        return null;
    }

    public List<Device> getByUserId(Long id) {
        List<DeviceEntity> deviceEntities = deviceRepository.getByUserId(id);
        return deviceEntities.stream().map(deviceMapper::toDomain).collect(Collectors.toList());
    }

    public List<Device> getByHubId(Long id) {
        List<DeviceEntity> deviceEntities = deviceRepository.getByHub_Id(id);
        return deviceEntities.stream().map(deviceMapper::toDomain).collect(Collectors.toList());
    }

    public Device delete(Long id) {
        if (deviceRepository.existsById(id)) {
            DeviceEntity entityToDelete = deviceRepository.getReferenceById(id);
            deviceRepository.delete(entityToDelete);
            return deviceMapper.toDomain(entityToDelete);
        }
        return null;
    }

    public Device update(Long id, Device device) {
        if (deviceRepository.existsById(id)) {
            DeviceEntity deviceEntity = deviceMapper.toEntity(device);
            DeviceEntity updatedEntity = deviceRepository.save(deviceEntity);
            updateState(device);
            return deviceMapper.toDomain(updatedEntity);
        }
        return null;
    }

    private void updateState(Device device) {
        SecurityEvent securityEvent = new SecurityEvent(
                "Log sent from device: " + device.getId() + " at " + Instant.now() + " with state: " + device.isCritical() + " and protection type: " + device.getProtection().getProtectionType() + ".",
                device.isCritical(),
                device.getHub().getId(),
                device.getId(),
                device.getProtection().getProtectionType(),
                Instant.now()
        );
        eventPublisher.publishSecurityEvent(securityEvent);
    }


}
