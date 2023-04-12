package com.example.software_doc_design.repository;

import com.example.software_doc_design.persistance.entity.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {
    @Query("SELECT n FROM NotificationEntity n join fetch n.device d where d.id = :id")
    List<NotificationEntity> getByDeviceId(Long id);
}
