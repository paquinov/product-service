package com.pe.paquinov.productservice.launcher;

import com.pe.paquinov.productservice.infrastructure.config.JourneyEngineProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.pe.paquinov.productservice.application.controllers",
								"com.pe.paquinov.productservice.application.mappers",
								"com.pe.paquinov.productservice.domain.services",
								"com.pe.paquinov.productservice.infrastructure.adapters",
								"com.pe.paquinov.productservice.launcher.config"})
@EnableConfigurationProperties(JourneyEngineProperties.class)
public class ProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApplication.class, args);
	}

}
