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

    public Mono<ServerResponse> getBitcoins(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(allBitcoinData.getBitcoins()));
    }

    // timestamp en segundos
    public Mono<ServerResponse> getBitcoinByTimeStamp(ServerRequest request) {
        long timestamp = Long.parseLong(request.pathVariable("timestamp"));
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(allBitcoinData.getBitcoinByTimeStamp(timestamp)));
    }

    // timestamp en segundos
    public Mono<ServerResponse> getBitcoinBetweenTimeStamps(ServerRequest request) {
        long timestampStart = Long.parseLong(request.pathVariable("timestampStart"));
        long timestampEnd = Long.parseLong(request.pathVariable("timestampEnd"));
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(allBitcoinData.getBitcoinBetweenTimeStamps(timestampStart, timestampEnd)));
    }
}
