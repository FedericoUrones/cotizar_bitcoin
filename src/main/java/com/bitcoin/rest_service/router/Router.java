package com.bitcoin.rest_service.router;

import com.bitcoin.rest_service.handler.Handler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class Router {

  /**
   * Adding routes
   * @param handler
   * @return routes
   */
  @Bean
  public RouterFunction<ServerResponse> route(Handler handler) {

    return RouterFunctions.route(RequestPredicates.GET("/getAllBitcoins"), handler::getBitcoins)
        .and(RouterFunctions.route(RequestPredicates.GET("/getBitcoinByTimestamp/{timestamp}"),
            handler::getBitcoinByTimeStamp))
        .and(RouterFunctions.route(RequestPredicates.GET("/getBitcoinBetweenTimestamp/{timestampStart}/{timestampEnd}"),
            handler::getAverageBetweenTimeStamps));
  }
}
