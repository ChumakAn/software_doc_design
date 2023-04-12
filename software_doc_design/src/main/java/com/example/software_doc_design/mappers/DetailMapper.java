package com.example.software_doc_design.mappers;

import com.example.software_doc_design.domain.Detail;
import com.example.software_doc_design.persistance.entity.DetailEntity;
import org.springframework.stereotype.Component;

@Component
public class DetailMapper {

    public DetailEntity toEntity(String details) {
        DetailEntity.DetailEntityBuilder detailEntityBuilder = DetailEntity.builder();

        return detailEntityBuilder.description(details).build();
    }

    public Detail toDomain(DetailEntity detail) {
        Detail.DetailBuilder detailBuilder = Detail.builder();

        return detailBuilder.description(detail.getDescription()).build();
    }
}
