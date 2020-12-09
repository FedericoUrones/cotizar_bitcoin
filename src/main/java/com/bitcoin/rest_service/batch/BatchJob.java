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
import java.time.Instant;

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

    /**
     * Calls Bitcoin price service and adds an item into allBitcoinData every fixedRate milliseconds
     */
    @Scheduled(fixedRate = fixedRate, initialDelay = initialDelay)
    public void getBitcoin() {
        LOGGER.info("Trying to obtain Bitcoin Price...");
        Mono<String> bitcoin = webClientService.callService();
        bitcoin.doOnSuccess(item -> {
            try {
                Bitcoin newBitcoin = objectMapper.readValue(item, Bitcoin.class);
                Instant instant = Instant.now();
                newBitcoin.setTimeStampSeconds(instant.getEpochSecond());
                allBitcoinData.addBitcoin(newBitcoin);
            } catch (JsonMappingException e) {
                LOGGER.error("Error while trying to map new Bitcoin price", e.getLocalizedMessage());
            } catch (JsonProcessingException e) {
                LOGGER.error("Error while trying to process new Bitcoin price", e.getLocalizedMessage());
            }
            LOGGER.info("New Bitcoin price added");
        }).doOnError(err -> {
            LOGGER.error("Error while trying to obtain new Bitcoin price", err.getLocalizedMessage());
        }).subscribe();
    }

}
