package com.github.tianxie.dddhexagonalspring.infrastracture.configuration;

import com.github.tianxie.dddhexagonalspring.DomainLayerApplication;
import com.github.tianxie.dddhexagonalspring.domain.repository.OrderRepository;
import com.github.tianxie.dddhexagonalspring.domain.service.OrderService;
import com.github.tianxie.dddhexagonalspring.domain.service.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = DomainLayerApplication.class)
public class BeanConfiguration {

    @Bean
    OrderService orderService(OrderRepository orderRepository) {
        return new OrderServiceImpl(orderRepository);
    }
}
