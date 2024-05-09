package com.app.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.app.domain.persistance.PriceRepository;
import com.app.application.useCases.PriceDTOUseCase;
import com.app.domain.service.PriceService;

@Configuration
public class ApplicationConfig {

    @Bean
    public PriceService myService(PriceRepository myRepo) {
        return new PriceService(myRepo);
    }

    @Bean
    public PriceDTOUseCase myUseCase(PriceService myService) {
        return new PriceDTOUseCase(myService);
    }

}
