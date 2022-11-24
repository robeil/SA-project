package com.robeil.customersservice.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.robeil.customersservice.service.dto.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Sender {

    @Autowired
    private KafkaTemplate<String, String>kafkaTemplate;

    public void send(ChangeEvent changeEvent){
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            String eventAsString = objectMapper.writeValueAsString(changeEvent);
            kafkaTemplate.send("final-customer-change-topic" , eventAsString);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
