package com.example.software_doc_design.events;

import com.example.software_doc_design.enums.ProtectionType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

@AllArgsConstructor
@Data
public class SecurityEvent {
    private String message;
    private boolean isCritical;
    private Long hubId;
    private Long deviceId;
    private ProtectionType protectionType;
    private Instant timestamp;
}
