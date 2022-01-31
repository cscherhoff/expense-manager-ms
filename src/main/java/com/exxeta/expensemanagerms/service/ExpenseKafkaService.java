package com.exxeta.expensemanagerms.service;

import com.exxeta.expenseservice.dtos.TransferDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ExpenseKafkaService {

    private final ObjectMapper mapper = new ObjectMapper();
    private final KafkaTemplate<String, String> kafkaTemplate;

    public ExpenseKafkaService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendKafkaMessage(TransferDto transferDto) throws JsonProcessingException {
        kafkaTemplate.send("account", "expense", mapper.writeValueAsString(transferDto));
    }
}
