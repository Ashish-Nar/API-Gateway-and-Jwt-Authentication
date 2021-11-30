package com.example.APIGateway;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class FallbackController {

    // if there is any issue in Order service then request will redirect to this orderServiceFallback
    @RequestMapping("/orderFallback")
    public Mono<String> orderServiceFallback(){
        return Mono.just("Order service is taking too long to respond or is down. Please try again later.");
    }
    // if there is any issue in Payment service then request will redirect to this paymentServiceFallback
    @RequestMapping("/paymentFallback")
    public Mono<String> paymentServiceFallback(){
        return Mono.just("Payment service is taking too long to respond or is down. Please try again later.");
    }
}
