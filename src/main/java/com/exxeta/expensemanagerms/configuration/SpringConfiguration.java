package com.exxeta.expensemanagerms.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = {"com.exxeta.expenseservice"})
@EnableJpaRepositories(basePackages = {"com.exxeta.expenseservice"})
public class SpringConfiguration {

}
