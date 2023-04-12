package com.example.software_doc_design.repository;

import com.example.software_doc_design.persistance.entity.HubEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HubRepository extends JpaRepository<HubEntity, Long> {

    List<HubEntity> getByUser_Id(Long id);

    @Query("SELECT h FROM HubEntity h JOIN FETCH h.devices d WHERE :id = d.id")
    HubEntity getByDeviceId(Long id);

}
