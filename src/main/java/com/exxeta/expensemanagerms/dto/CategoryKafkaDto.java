package com.exxeta.expensemanagerms.dto;

import java.math.BigDecimal;

public class CategoryKafkaDto {

    private String categoryName;

    private BigDecimal budget;

    public CategoryKafkaDto() {
    }

    public CategoryKafkaDto(String categoryName, BigDecimal budget) {
        this.categoryName = categoryName;
        this.budget = budget;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public BigDecimal getBudget() {
        return budget;
    }
}
