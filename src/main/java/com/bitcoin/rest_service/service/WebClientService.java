package com.bitcoin.rest_service.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Service
public class WebClientService {
    private final WebClient webClient;

    private static final String bitcoinUrl = "https://cex.io";

    public WebClientService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(bitcoinUrl).build();
    }

    public Mono<String> callService() {
        System.out.println("callService");
        return this.webClient.get().uri("/api/last_price/BTC/USD")
                .retrieve().bodyToMono(String.class);
    }

}
