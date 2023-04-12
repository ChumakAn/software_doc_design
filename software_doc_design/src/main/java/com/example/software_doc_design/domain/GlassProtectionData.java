package com.example.software_doc_design.domain;


import com.example.software_doc_design.enums.DeviceState;
import com.example.software_doc_design.enums.ProtectionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class GlassProtectionData extends DeviceProtectionData {
    private ProtectionType protectionType = ProtectionType.GLASS_PROTECTION;
    private final static Integer MAXIMUM_LEVEL = 30;
    private Integer soundLevel;

    public boolean shouldTriggerAlarm() {
        return soundLevel >= MAXIMUM_LEVEL;
    }

    @Builder
    public GlassProtectionData(
            String name,
            ProtectionType protectionType,
            DeviceState deviceState,
            Integer soundLevel
    ) {
        super(name, protectionType, deviceState);
        this.soundLevel = soundLevel;
    }
}
