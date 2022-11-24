package com.robeil.customersservice;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableDiscoveryClient
@EnableKafka
public class CustomersServiceApplication {

    @Bean
    public ModelMapper getModelMapper(){
        return new ModelMapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(CustomersServiceApplication.class, args);
    }

}
