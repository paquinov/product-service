package com.pe.paquinov.productservice.application.mappers;

import com.pe.paquinov.productservice.application.dto.StepNotifierDTO;
import com.pe.paquinov.productservice.domain.data.JourneyStepResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StepNotifierFieldsMapper extends StepNotifierMapper {

    @Mapping(target = "journeyId", source = "journeyStepResponse.journeyId")
    @Mapping(target = "stepId", source = "journeyStepResponse.stepId")
    @Mapping(target = "stepType", source = "journeyStepResponse.stepType")
    @Override
    StepNotifierDTO buildDTO(final JourneyStepResponse journeyStepResponse);

}
