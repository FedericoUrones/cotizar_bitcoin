package com.bitcoin.rest_service.batch;

import com.bitcoin.rest_service.allData.AllBitcoinData;
import com.bitcoin.rest_service.pojo.Bitcoin;
import com.bitcoin.rest_service.service.WebClientService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.lang.invoke.MethodHandles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import reactor.core.publisher.Mono;

@Component
public class BatchJob {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final long fixedRate = 10000;
    private final long initialDelay = 0;

    @Autowired
    private WebClientService webClientService;

    @Autowired
    AllBitcoinData allBitcoinData;

    ObjectMapper objectMapper = new ObjectMapper();

    @Scheduled(fixedRate = fixedRate, initialDelay = initialDelay)
    public void getBitcoin() {
        LOGGER.info("Tratando de obtener Cotización de Bitcoin...");
        Mono<String> bitcoin = webClientService.callService();
        bitcoin.doOnSuccess(item -> {
            try {
                allBitcoinData.getBitcoins().add(objectMapper.readValue(item, Bitcoin.class));
            } catch (JsonMappingException e) {
                LOGGER.error("Error al intentar mapear Cotización de Bitcoin", e.getLocalizedMessage());
            } catch (JsonProcessingException e) {
                LOGGER.error("Error al intentar mapear Cotización de Bitcoin", e.getLocalizedMessage());
            }
            LOGGER.info("Nueva Cotización de Bitcoin agregada");
            System.out.println(allBitcoinData.getBitcoins().size());
        }).doOnError(err -> {
            LOGGER.error("Error al intentar obtener Cotización de Bitcoin", err.getLocalizedMessage());
        }).subscribe();
    }

}
