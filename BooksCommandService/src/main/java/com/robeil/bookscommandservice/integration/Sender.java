package com.robeil.bookscommandservice.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Sender {

    @Autowired
    private KafkaTemplate kafkaTemplate;
    public void send(ChangeEvent changeEvent){
        ObjectMapper objectMapper = new ObjectMapper();

        try{
            String changeEventAsString = objectMapper.writeValueAsString(changeEvent);
            kafkaTemplate.send("final-book-change-topic" , changeEventAsString);
        } catch (JsonProcessingException e){
            e.printStackTrace();
        }

    }
}
