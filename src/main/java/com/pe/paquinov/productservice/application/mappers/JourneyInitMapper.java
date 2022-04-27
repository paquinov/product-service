package com.pe.paquinov.productservice.application.mappers;

import com.pe.paquinov.productservice.application.dto.JourneyInitDTO;

public interface JourneyInitMapper {

    JourneyInitDTO buildDTO(final String journeyId);

}
