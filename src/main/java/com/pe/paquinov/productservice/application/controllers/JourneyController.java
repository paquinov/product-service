package com.pe.paquinov.productservice.application.controllers;

import com.pe.paquinov.productservice.application.dto.JourneyInitDTO;
import com.pe.paquinov.productservice.application.dto.StepNotifierDTO;
import com.pe.paquinov.productservice.domain.ports.api.JourneyManagerPort;
import com.pe.paquinov.productservice.application.mappers.JourneyInitMapper;
import com.pe.paquinov.productservice.application.mappers.StepNotifierMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/journeys")
@RequiredArgsConstructor
@Slf4j
public class JourneyController {

    private final JourneyManagerPort journeyManagerPort;
    private final JourneyInitMapper journeyInitMapper;
    private final StepNotifierMapper stepNotifierMapper;

    @PostMapping("/start")
    public Mono<ResponseEntity<JourneyInitDTO>> startJourney(@RequestParam("identityDocNumber") String identityDocNumber) {
        return journeyManagerPort.startJourney(identityDocNumber)
                                .doOnSuccess(journeyId -> log.info("Journey was initialized successfully : {}", journeyId))
                                .map(journeyId -> ResponseEntity.ok(journeyInitMapper.buildDTO(journeyId)));
    }

    @GetMapping("/{journeyId}/steps")
    public Flux<ServerSentEvent<StepNotifierDTO>> notifySteps(@PathVariable("journeyId") String journeyId) {
        return journeyManagerPort.subscribeToGetSteps(journeyId)
                                .map(stepNotifierMapper::buildDTO)
                                .map(stepNotifier -> ServerSentEvent.builder(stepNotifier).build());
    }

}
