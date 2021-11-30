package com.example.orderService;


import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/order")
@RefreshScope
public class OrderController {

    private static final String ORDER_SERVICE = "orderService";

    @Autowired
    private ServiceOrder service;

    @Value("${message:default message}")
    private String message;

    @PostMapping("/makeOrder")
    @CircuitBreaker(name=ORDER_SERVICE, fallbackMethod = "orderFallback")
    public TransactionResponse bookOrder(@RequestBody TransactionRequest request){

//        return service.saveOrder(order);
        return service.saveOrder(request);
    }

    private TransactionResponse orderFallback(Exception e) {
        System.out.println("Service is down, come again later.");
        return null;
    }

    @GetMapping("/message")
    public String getMessage(){
        return message;
    }

}
