package com.pe.paquinov.productservice.launcher.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Sinks;

@Configuration
public class PublisherConfiguration {

    @Bean
    public Sinks.Many<Object> sink() {
        return Sinks.many().multicast().onBackpressureBuffer();
    }

}
