package com.thall.wigelltravel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import java.util.logging.LogManager;
import java.util.logging.Logger;

@SpringBootApplication

public class WigellTravelApplication {

    public static void main(String[] args) {
        SpringApplication.run(WigellTravelApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(){
        RestTemplate restTemplate = new RestTemplate();


        restTemplate.getInterceptors().add((ClientHttpRequestInterceptor) (request, body, execution) -> {
            request.getHeaders().set("X-Api-Key", "weayUKQG8fqGwf7sZT/pFQ==Eekg57klvP0q4kH0");
            return execution.execute(request, body);
        });

        return restTemplate;
    }



}
