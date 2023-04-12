package com.example.software_doc_design.mappers;

import com.example.software_doc_design.domain.SoundAlarm;
import com.example.software_doc_design.persistance.entity.SoundAlarmEntity;
import org.springframework.stereotype.Component;

@Component
public class SoundAlarmMapper {
    private final HubMapper hubMapper;

    public SoundAlarmMapper(HubMapper hubMapper) {
        this.hubMapper = hubMapper;
    }

    public SoundAlarm toDomain(SoundAlarmEntity soundAlarmEntity) {
        SoundAlarm.SoundAlarmBuilder soundAlarmBuilder = SoundAlarm.builder();
        return soundAlarmBuilder
                .soundAlarmTimestamp(soundAlarmEntity.getSoundAlarmTimestamp())
                .soundAlarmType(soundAlarmEntity.getSoundAlarmType())
                .hub(hubMapper.toDomain(soundAlarmEntity.getHub()))
                .build();
    }

    public SoundAlarmEntity toEntity(SoundAlarm soundAlarm) {
        SoundAlarmEntity.SoundAlarmEntityBuilder soundAlarmEntityBuilder = SoundAlarmEntity.builder();
        return soundAlarmEntityBuilder
                .soundAlarmTimestamp(soundAlarm.getSoundAlarmTimestamp())
                .soundAlarmType(soundAlarm.getSoundAlarmType())
                .hub(hubMapper.toEntity(soundAlarm.getHub()))
                .build();
    }
}
