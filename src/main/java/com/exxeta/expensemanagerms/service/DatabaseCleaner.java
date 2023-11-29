package com.exxeta.expensemanagerms.service;

import com.exxeta.expenseservice.repositories.ArticleRepository;
import com.exxeta.expenseservice.repositories.CategoryRepository;
import com.exxeta.expenseservice.repositories.ExpenseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Profile("cleardb")
public class DatabaseCleaner {

    private final ArticleRepository articleRepository;
    private final CategoryRepository categoryRepository;
    private final ExpenseRepository expenseRepository;

    private final Logger LOGGER = LoggerFactory.getLogger(DatabaseCleaner.class);

    public DatabaseCleaner(ArticleRepository articleRepository, CategoryRepository categoryRepository, ExpenseRepository expenseRepository) {
        this.articleRepository = articleRepository;
        this.categoryRepository = categoryRepository;
        this.expenseRepository = expenseRepository;
    }

    @PostConstruct
    void setUpDatabases() {
        LOGGER.info("Clearing databases...");
        articleRepository.deleteAll();
        categoryRepository.deleteAll();
        expenseRepository.deleteAll();
        LOGGER.info("... all databases are empty");
    }
}
