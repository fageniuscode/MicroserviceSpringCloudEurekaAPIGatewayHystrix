package com.fageniuscode.cloud.gateway;

import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Mono;

public class FallbackController {

    @RequestMapping("/orderFallBack")
    public Mono<String> orderServiceFallBack(){
        return Mono.just("Order service is taking too long to response or is down. Please try again later");
    }

    @RequestMapping("/paymentFallBack")
    public Mono<String> paymentServiceFallBack(){
        return Mono.just("Payment service is taking too long to response or is down. Please try again later");
    }
}
