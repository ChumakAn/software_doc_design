package com.example.software_doc_design.service;

import com.example.software_doc_design.domain.Hub;
import com.example.software_doc_design.domain.SoundAlarm;
import com.example.software_doc_design.enums.ProtectionType;
import com.example.software_doc_design.enums.SoundAlarmType;
import com.example.software_doc_design.mappers.SoundAlarmMapper;
import com.example.software_doc_design.repository.SoundAlarmRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SoundAlarmService {
    private final SoundAlarmRepository soundAlarmRepository;
    private final SoundAlarmMapper soundAlarmMapper;

    private final HubService hubService;

    public SoundAlarmService(SoundAlarmRepository soundAlarmRepository, SoundAlarmMapper soundAlarmMapper, HubService hubService) {
        this.soundAlarmRepository = soundAlarmRepository;
        this.soundAlarmMapper = soundAlarmMapper;
        this.hubService = hubService;
    }

    public void triggerSoundAlarm(ProtectionType protectionType, Long hubId, Instant soundAlarmTimestamp) {
        Hub hub = hubService.getHubById(hubId);
        SoundAlarm soundAlarm = SoundAlarm.builder()
                .soundAlarmType(getSoundAlarmType(protectionType))
                .soundAlarmTimestamp(soundAlarmTimestamp)
                .hub(hub)
                .build();
        save(soundAlarm);
    }

    public SoundAlarm save(SoundAlarm soundAlarm) {
        return soundAlarmMapper.toDomain(soundAlarmRepository.save(soundAlarmMapper.toEntity(soundAlarm)));
    }

    public List<SoundAlarm> getAll() {
        return soundAlarmRepository.findAll().stream().map(soundAlarmMapper::toDomain).collect(Collectors.toList());
    }

    public SoundAlarm getById(Long id) {
        return soundAlarmMapper.toDomain(soundAlarmRepository.getReferenceById(id));
    }

    private SoundAlarmType getSoundAlarmType(ProtectionType protectionType) {
        return switch (protectionType) {
            case FIRE_PROTECTION -> SoundAlarmType.SMOKE_OR_FIRE;
            case LEAKS_PROTECTION -> SoundAlarmType.WATER_LEAK;
            case GLASS_PROTECTION -> SoundAlarmType.GLASS_BREAK;
            case MOTION_PROTECTION -> SoundAlarmType.MOTION_DETECTION;
        };
    }
}
