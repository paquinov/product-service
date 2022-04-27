package com.pe.paquinov.productservice.domain.services;

import com.pe.paquinov.productservice.domain.ports.api.JourneyManagerPort;
import com.pe.paquinov.productservice.domain.ports.spi.JourneyEnginePort;
import com.pe.paquinov.productservice.domain.data.JourneyStepResponse;
import com.pe.paquinov.productservice.domain.data.StartJourneyResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

import java.util.concurrent.CompletableFuture;

@Component
@Slf4j
@RequiredArgsConstructor
public class JourneyManagerServiceImpl implements JourneyManagerPort {

    private final Sinks.Many<Object> sink;
    private final JourneyEnginePort journeyEnginePort;

    @Override
    public Mono<String> startJourney(String identityDocNumber) {
        return journeyEnginePort.startJourney(identityDocNumber)
                    .map(StartJourneyResponse::getJourneyId)
                    .doOnSuccess(journeyId -> CompletableFuture.runAsync(() ->
                                                    journeyEnginePort.subscribeOnJourneyProcess(journeyId)
                                                                .doOnNext(step -> {
                                                                    log.info("Step : {}", step.getStepId());
                                                                    sink.tryEmitNext(step);
                                                                })
                                                                .doOnComplete(() -> {
                                                                    log.info("Workflow is completed successfully");
                                                                    sink.tryEmitComplete();
                                                                })
                                                                .doFinally(signal -> log.info("Final signal : {}", signal.name()))
                                                                .subscribe()));
    }

    @Override
    public Flux<JourneyStepResponse> subscribeToGetSteps(String journeyId) {
        return sink.asFlux()
                    .map(JourneyStepResponse.class::cast)
                    .filter(step -> journeyId.equalsIgnoreCase(step.getJourneyId()));
    }

}
