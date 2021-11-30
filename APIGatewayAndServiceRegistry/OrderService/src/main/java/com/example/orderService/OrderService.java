package com.example.orderService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
public class OrderService {

    @Bean
    @LoadBalanced   // this is for client side load balancing
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    public static void main(String args[]){
        SpringApplication.run(OrderService.class, args);
    }
}

