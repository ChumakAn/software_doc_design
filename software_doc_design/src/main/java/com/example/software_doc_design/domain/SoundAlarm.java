package com.example.software_doc_design.domain;

import com.example.software_doc_design.enums.SoundAlarmType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.Instant;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class SoundAlarm {
    private SoundAlarmType soundAlarmType;
    private Instant soundAlarmTimestamp;
    private Hub hub;
}
