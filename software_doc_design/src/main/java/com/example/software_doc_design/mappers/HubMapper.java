package com.example.software_doc_design.mappers;

import com.example.software_doc_design.domain.Hub;
import com.example.software_doc_design.persistance.entity.HubEntity;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class HubMapper {

    private final UserMapper userMapper;

    public HubMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public Hub toDomain(HubEntity hubEntity) {
        Hub.HubBuilder hubBuilder = Hub.builder();
        return hubBuilder
                .id(hubEntity.getId())
                .hubType(hubEntity.getHubType())
                .user(userMapper.toDomain(hubEntity.getUser()))
                .eventLog(hubEntity.getEventLog())
                .build();

    }

    public HubEntity toEntity(Hub hub) {
        HubEntity.HubEntityBuilder hubEntityBuilder = HubEntity.builder();
        return hubEntityBuilder
                .id(hub.getId())
                .hubType(hub.getHubType())
                .user(userMapper.toEntity(hub.getUser()))
                .eventLog(hub.getEventLog())
                .build();
    }
}
