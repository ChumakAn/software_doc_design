package com.example.software_doc_design.service;

import com.example.software_doc_design.domain.Hub;
import com.example.software_doc_design.mappers.HubMapper;
import com.example.software_doc_design.persistance.entity.HubEntity;
import com.example.software_doc_design.repository.HubRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HubService {
    private final HubRepository hubRepository;
    private final HubMapper hubMapper;

    public HubService(HubRepository hubRepository, HubMapper hubMapper) {
        this.hubRepository = hubRepository;
        this.hubMapper = hubMapper;
    }

    public Hub save(Hub hub) {
        HubEntity savedHub = hubRepository.save(hubMapper.toEntity(hub));
        return hubMapper.toDomain(savedHub);
    }

    public Hub update(Long id, Hub hub) {
        if (hubRepository.existsById(id)) {
            HubEntity hubEntity = hubMapper.toEntity(hub);
            hubEntity.setId(id);
            hubRepository.save(hubEntity);
            return hubMapper.toDomain(hubEntity);
        }
        return null;
    }

    public Hub delete(Long id) {
        if (hubRepository.existsById(id)) {
            HubEntity hubEntity = hubRepository.getReferenceById(id);
            hubRepository.delete(hubEntity);
            return hubMapper.toDomain(hubEntity);
        }
        return null;
    }

    public List<Hub> getByUserId(Long id) {
        List<HubEntity> hubEntities = hubRepository.getByUser_Id(id);
        return hubEntities.stream().map(hubMapper::toDomain).toList();
    }

    public Hub getHubById(Long id) {
        HubEntity hubEntity = hubRepository.getReferenceById(id);
        return hubMapper.toDomain(hubEntity);
    }
}
