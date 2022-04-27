package com.pe.paquinov.productservice.domain.ports.spi;

import com.pe.paquinov.productservice.domain.data.JourneyStepResponse;
import com.pe.paquinov.productservice.domain.data.StartJourneyResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface JourneyEnginePort {

    Mono<StartJourneyResponse> startJourney(final String identityDocNumber);
    Flux<JourneyStepResponse> subscribeOnJourneyProcess(final String journeyId);

}
