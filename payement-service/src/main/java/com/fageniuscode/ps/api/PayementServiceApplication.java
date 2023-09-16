package com.fageniuscode.ps.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PayementServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PayementServiceApplication.class, args);
    }

}
