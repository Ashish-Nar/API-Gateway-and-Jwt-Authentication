package com.example.paymentService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    ServicePayment service;

    @PostMapping("/makePayment")
//    @CircuitBreaker(name = "PAYMENT-SERVICE", fallbackMethod = "paymentFallback")
    public Payment makePayment(@RequestBody Payment payment){
        return service.makePayment(payment);
    }

    // API to verify the API Gateway
    @GetMapping("/{orderId}")
    public Payment findPaymentHistoryByOrderId(@PathVariable int orderId){
        return service.findPaymentHistoryByOrderId(orderId);
    }

    // fallback method
//    private Payment paymentFallback(Exception e){
//        System.out.println("Payment Service is Down... Try again later...");
//        return null;
//    }
}
