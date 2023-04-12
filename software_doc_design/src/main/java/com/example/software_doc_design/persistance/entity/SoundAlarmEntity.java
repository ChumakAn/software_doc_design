package com.example.software_doc_design.persistance.entity;

import com.example.software_doc_design.enums.SoundAlarmType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.Instant;

@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "sound_alarm")
@Builder
public class SoundAlarmEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private SoundAlarmType soundAlarmType;
    @Column(name = "timestamp")
    private Instant soundAlarmTimestamp;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hub_id")
    @ToString.Exclude
    private HubEntity hub;
}
