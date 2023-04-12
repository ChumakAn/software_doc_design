package com.example.software_doc_design.domain;

import com.example.software_doc_design.enums.DeviceState;
import com.example.software_doc_design.enums.ProtectionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class FireProtectionData extends DeviceProtectionData {
    private ProtectionType protectionType = ProtectionType.FIRE_PROTECTION;
    private static final Integer MAXIMUM_LEVEL = 70;
    private Integer levelThreshold;

    public boolean shouldTriggerAlarm() {
        return levelThreshold > MAXIMUM_LEVEL;
    }

    @Builder
    public FireProtectionData(
            String name,
            ProtectionType protectionType,
            DeviceState deviceState,
            Integer levelThreshold
    ) {
        super(name, protectionType, deviceState);
        this.levelThreshold = levelThreshold;
    }
}
