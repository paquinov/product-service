package com.pe.paquinov.productservice.domain.ports.api;

import com.pe.paquinov.productservice.domain.data.JourneyStepResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface JourneyManagerPort {

    Mono<String> startJourney(final String identityDocNumber);
    Flux<JourneyStepResponse> subscribeToGetSteps(final String journeyId);

}
