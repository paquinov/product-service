package com.pe.paquinov.productservice.infrastructure.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "adapters.journey-engine")
public class JourneyEngineProperties {

    private String host;
    private String startJourneyUri;
    private String subscribeOnProcessUri;

}
