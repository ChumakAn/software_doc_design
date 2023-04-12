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
public class LeaksProtectionData extends DeviceProtectionData {
    private ProtectionType protectionType = ProtectionType.LEAKS_PROTECTION;
    private final static Integer MAXIMUM_LEVEL = 30;
    private Integer waterLevel;

    public boolean shouldTriggerAlarm() {
        return waterLevel >= MAXIMUM_LEVEL;
    }

    @Builder
    public LeaksProtectionData(
            String name,
            ProtectionType protectionType,
            DeviceState deviceState,
            Integer waterLevel
    ) {
        super(name, protectionType, deviceState);
        this.waterLevel = waterLevel;
    }
}
