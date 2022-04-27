package com.pe.paquinov.productservice.application.mappers;

import com.pe.paquinov.productservice.application.dto.StepNotifierDTO;
import com.pe.paquinov.productservice.domain.data.JourneyStepResponse;

public interface StepNotifierMapper {

    StepNotifierDTO buildDTO(final JourneyStepResponse journeyStepResponse);

}
