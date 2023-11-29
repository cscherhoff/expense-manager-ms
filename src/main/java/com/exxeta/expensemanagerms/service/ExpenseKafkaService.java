package com.exxeta.expensemanagerms.service;

import com.exxeta.expensemanagerms.dto.CategoryKafkaDto;
import com.exxeta.expenseservice.entities.Category;
import com.exxeta.expenseservice.services.CategoryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.kafka.core.KafkaTemplate;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class ExpenseKafkaService {

    private final Logger logger = LoggerFactory.getLogger(ExpenseKafkaService.class);
    private final ObjectMapper objectMapper = new ObjectMapper();

    private final CategoryService categoryService;

    public ExpenseKafkaService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @KafkaListener(topics = "allocation", groupId = "expense-manager",autoStartup = "${listen.auto.start:true}")
    public void consumeMessage(ConsumerRecord<String, String> cr) {
        logger.info("Received message: " + cr.value() + " with the key " + cr.key());
        try {
            final CategoryKafkaDto[] categoryKafkaDtos = objectMapper.readValue(cr.value(), CategoryKafkaDto[].class);
            categoryService.updateBudgetForCategories(mapCategories(cr.key(), categoryKafkaDtos));
        } catch (JsonProcessingException jsonProcessingException) {
            logger.error("Could not parse json from the kafka message: " + cr.value());
        }
    }

    private Collection<Category> mapCategories(String userId, CategoryKafkaDto[] categoryKafkaDtos) {
        List<Category> categoryList = new ArrayList<>();
        for (CategoryKafkaDto categoryKafkaDto: categoryKafkaDtos) {
            categoryList.add(
                    new Category(userId, categoryKafkaDto.getCategoryName(), categoryKafkaDto.getBudget(), categoryKafkaDto.getBudget())
            );
        }
        return categoryList;
    }
//    private final ObjectMapper mapper = new ObjectMapper();
//    private final KafkaTemplate<String, String> kafkaTemplate;

//    public ExpenseKafkaService(KafkaTemplate<String, String> kafkaTemplate) {
//        this.kafkaTemplate = kafkaTemplate;
//    }

//    public void sendKafkaMessage(TransferDto transferDto) throws JsonProcessingException {
//        kafkaTemplate.send("account", "expense", mapper.writeValueAsString(transferDto));
//    }
}
