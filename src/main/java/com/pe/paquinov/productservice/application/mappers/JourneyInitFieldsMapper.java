package com.pe.paquinov.productservice.application.mappers;

import com.pe.paquinov.productservice.application.dto.JourneyInitDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface JourneyInitFieldsMapper extends JourneyInitMapper {

    @Mapping(target = "journeyId", source = "journeyId")
    @Override
    JourneyInitDTO buildDTO(final String journeyId);

}
