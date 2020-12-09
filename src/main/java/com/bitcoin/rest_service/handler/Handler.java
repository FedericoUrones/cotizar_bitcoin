package com.bitcoin.rest_service.handler;

import com.bitcoin.rest_service.allData.AllBitcoinData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

@Component
public class Handler {

    @Autowired
    AllBitcoinData allBitcoinData;

    /**
     * Returns all the bitcoins prices stored in service
     * 
     * @param request
     * @return all bitcoins prices
     */
    public Mono<ServerResponse> getBitcoins(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(allBitcoinData.getBitcoins()));
    }

    /**
     * Returns a bitcoin price stored in service filtered by timestamp
     * 
     * @param request: with timestamp path variable, in seconds
     * @return all bitcoins
     */
    public Mono<ServerResponse> getBitcoinByTimeStamp(ServerRequest request) {
        long timestamp = Long.parseLong(request.pathVariable("timestamp"));
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(allBitcoinData.getBitcoinByTimeStamp(timestamp)));
    }

    /**
     * Returns bitcoin average price between two timestamps, and percent difference between average
     * and max value stored in service
     * 
     * @param request: with timestampStart and timestampEnd path variables, in seconds
     * @return average price and percent difference
     */
    public Mono<ServerResponse> getAverageBetweenTimeStamps(ServerRequest request) {
        long timestampStart = Long.parseLong(request.pathVariable("timestampStart"));
        long timestampEnd = Long.parseLong(request.pathVariable("timestampEnd"));
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(allBitcoinData.getAverageAndPercent(timestampStart, timestampEnd)));
    }
}
