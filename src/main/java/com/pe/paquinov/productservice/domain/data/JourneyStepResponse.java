package com.pe.paquinov.productservice.domain.data;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class JourneyStepResponse {

    private String code;
    private String message;
    private String journeyId;
    private StepType stepType;
    private String stepId;

}
