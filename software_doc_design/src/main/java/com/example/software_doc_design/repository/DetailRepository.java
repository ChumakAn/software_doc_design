package com.example.software_doc_design.repository;

import com.example.software_doc_design.persistance.entity.DetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailRepository extends JpaRepository<DetailEntity, Long> {
}
