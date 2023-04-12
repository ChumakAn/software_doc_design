package com.example.software_doc_design.domain;

import com.example.software_doc_design.enums.DeviceState;
import com.example.software_doc_design.enums.DeviceType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public  class Device {
    private Long id;
    private String name;
    private DeviceType type;
    private String batteryLevel;
    private String details;
    private User user;
    private Hub hub;
    private DeviceProtectionData protection;

    public boolean isCritical() {
        return protection.deviceState == DeviceState.ALARM_STATE
                || protection.deviceState == DeviceState.BATTERY_LOW
                || protection.shouldTriggerAlarm();
    }
}
