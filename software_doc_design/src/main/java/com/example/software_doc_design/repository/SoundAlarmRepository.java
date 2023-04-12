package com.example.software_doc_design.repository;

import com.example.software_doc_design.persistance.entity.SoundAlarmEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoundAlarmRepository extends JpaRepository<SoundAlarmEntity, Long> {
}
