package com.pe.paquinov.productservice.infrastructure.adapters;

import com.pe.paquinov.productservice.domain.data.JourneyStepResponse;
import com.pe.paquinov.productservice.domain.data.StartJourneyRequest;
import com.pe.paquinov.productservice.domain.data.StartJourneyResponse;
import com.pe.paquinov.productservice.domain.ports.spi.JourneyEnginePort;
import com.pe.paquinov.productservice.infrastructure.config.JourneyEngineProperties;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@Slf4j
@RequiredArgsConstructor
public class JourneyEngineAdapter implements JourneyEnginePort {

    private final JourneyEngineProperties properties;

    @Override
    public Mono<StartJourneyResponse> startJourney(String identityDocNumber) {
        return WebClient.create(properties.getHost())
                        .post()
                        .uri(properties.getStartJourneyUri())
                        .bodyValue(StartJourneyRequest.builder()
                                                    .documentNumber(identityDocNumber)
                                                    .build())
                        .retrieve()
                        .bodyToMono(StartJourneyResponse.class);
    }

    @Override
    public Flux<JourneyStepResponse> subscribeOnJourneyProcess(String journeyId) {
        return WebClient.create(properties.getHost())
                        .get()
                        .uri(uriBuilder -> uriBuilder
                                                .path(properties.getSubscribeOnProcessUri())
                                                .build(journeyId))
                        .retrieve()
                        .bodyToFlux(JourneyStepResponse.class)
                        .takeWhile(step -> StringUtils.isNotBlank(step.getStepId()));
    }

}
