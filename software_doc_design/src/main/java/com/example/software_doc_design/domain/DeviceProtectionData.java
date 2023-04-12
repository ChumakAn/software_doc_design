package com.example.software_doc_design.domain;

import com.example.software_doc_design.enums.DeviceState;
import com.example.software_doc_design.enums.ProtectionType;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


@Data
@NoArgsConstructor
@JsonTypeInfo( use = JsonTypeInfo.Id.NAME, property = "protectionType")
@JsonSubTypes({
        @JsonSubTypes.Type(value = FireProtectionData.class, name = "FIRE_PROTECTION"),
        @JsonSubTypes.Type(value = GlassProtectionData.class, name = "GLASS_PROTECTION"),
        @JsonSubTypes.Type(value = LeaksProtectionData.class, name = "LEAKS_PROTECTION"),
        @JsonSubTypes.Type(value = MotionProtectionData.class, name = "MOTION_PROTECTION"),
})
public abstract class DeviceProtectionData {
    protected String name;
    public ProtectionType protectionType;
    protected DeviceState deviceState;

    public DeviceProtectionData(String name, ProtectionType protectionType, DeviceState deviceState) {
        this.name = name;
        this.protectionType = protectionType;
        this.deviceState = deviceState;
    }

    public boolean shouldTriggerAlarm() {
        return false;
    }
}
