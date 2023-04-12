package com.example.software_doc_design.domain;

import com.example.software_doc_design.enums.HubType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class Hub {
    private Long id;
    private HubType hubType;
    private String eventLog;
    private User user;
}
