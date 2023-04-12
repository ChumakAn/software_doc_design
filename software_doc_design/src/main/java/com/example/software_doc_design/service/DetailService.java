package com.example.software_doc_design.service;

import com.example.software_doc_design.domain.Detail;
import com.example.software_doc_design.mappers.DetailMapper;
import com.example.software_doc_design.repository.DetailRepository;
import org.springframework.stereotype.Service;

@Service
public class DetailService {
    private final DetailRepository detailRepository;
    private final DetailMapper detailMapper;

    public DetailService(DetailRepository detailRepository, DetailMapper detailMapper) {
        this.detailRepository = detailRepository;
        this.detailMapper = detailMapper;
    }

    public Detail save(String detail) {
        return detailMapper.toDomain(detailRepository.save(detailMapper.toEntity(detail)));
    }

    public Detail getById(Long id) {
        return detailMapper.toDomain(detailRepository.getReferenceById(id));
    }
}
