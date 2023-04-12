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
public class MotionProtectionData extends DeviceProtectionData {
    private ProtectionType protectionType = ProtectionType.MOTION_PROTECTION;
    private final static Integer MAXIMUM_COUNT = 20;
    private Integer movementCount;

    public boolean shouldTriggerAlarm() {
        return movementCount >= MAXIMUM_COUNT;
    }

    @Builder
    public MotionProtectionData(
            String name,
            ProtectionType protectionType,
            DeviceState deviceState,
            Integer movementCount
    ) {
        super(name, protectionType, deviceState);
        this.movementCount = movementCount;
    }

}
