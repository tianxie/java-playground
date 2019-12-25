package com.github.tianxie.dddhexagonalspring.infrastracture.configuration;

import com.github.tianxie.dddhexagonalspring.infrastracture.repository.SpringDataOrderRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackageClasses = SpringDataOrderRepository.class)
public class MongoDBConfiguration {

}
