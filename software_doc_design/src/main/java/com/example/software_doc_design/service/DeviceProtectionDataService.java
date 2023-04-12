package com.example.software_doc_design.service;

import com.example.software_doc_design.domain.DeviceProtectionData;
import com.example.software_doc_design.mappers.ProtectionDataMapper;
import com.example.software_doc_design.persistance.entity.DeviceProtectionDataEntity;
import com.example.software_doc_design.repository.DeviceProtectionDataRepository;
import org.springframework.stereotype.Service;

@Service
public class DeviceProtectionDataService {
    private final DeviceProtectionDataRepository deviceProtectionDataRepository;
    private final ProtectionDataMapper deviceProtectionDataMapper;

    public DeviceProtectionDataService(DeviceProtectionDataRepository deviceProtectionDataRepository, ProtectionDataMapper deviceProtectionDataMapper) {
        this.deviceProtectionDataRepository = deviceProtectionDataRepository;
        this.deviceProtectionDataMapper = deviceProtectionDataMapper;
    }

    public DeviceProtectionData save(DeviceProtectionData protectionData) {
        return deviceProtectionDataMapper.toDomain(deviceProtectionDataRepository
                .save(deviceProtectionDataMapper
                        .toEntity(protectionData)));
    }

    public DeviceProtectionData save(DeviceProtectionDataEntity protectionData) {
        return deviceProtectionDataMapper.toDomain(deviceProtectionDataRepository
                .save(protectionData));
    }

    public DeviceProtectionData getById(Long id) {
        return deviceProtectionDataMapper.toDomain(deviceProtectionDataRepository.getReferenceById(id));
    }
}
