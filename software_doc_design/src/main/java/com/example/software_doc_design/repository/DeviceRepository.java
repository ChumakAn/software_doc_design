package com.example.software_doc_design.repository;

import com.example.software_doc_design.persistance.entity.DeviceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceRepository extends JpaRepository<DeviceEntity, Long> {
    List<DeviceEntity> getByHub_Id(Long id);

    @Query("select d from DeviceEntity d  join fetch d.user u where u.id = :id")
    List<DeviceEntity> getByUserId(Long id);
}
