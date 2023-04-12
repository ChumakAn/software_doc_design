package com.example.software_doc_design.persistance.entity;

import com.example.software_doc_design.enums.DeviceState;
import com.example.software_doc_design.enums.ProtectionType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "protection_type")
@Builder
public class DeviceProtectionDataEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private ProtectionType protectionType;
    @Column(name = "device_state")
    @Enumerated(EnumType.STRING)
    private DeviceState deviceState;
    @Column(name = "installation_method")
    private String installationMethod;
    @Column(name = "level_threshold")
    private Integer levelThreshold;
    @Column(name = "movement_count")
    private Integer movementCount;
    @Column(name = "sound_level")
    private Integer soundLevel;
    @Column(name = "water_level")
    private Integer waterLevel;

}
