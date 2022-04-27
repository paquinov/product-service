package com.pe.paquinov.productservice.application.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StepNotifierDTO {

    private String journeyId;
    private String stepId;
    private String stepType;

}
