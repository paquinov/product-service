package com.pe.paquinov.productservice.application.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JourneyInitDTO {

    private String journeyId;
    // TODO : the first step must be returned, but we have to wait until Journey Manager API brings that information
    //private StepDTO step;

}
