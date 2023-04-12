package com.example.software_doc_design.domain;

import com.example.software_doc_design.enums.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class Notification {
    private NotificationType notificationType;
    private String message;
    private Hub hub;
    private Device device;
}
