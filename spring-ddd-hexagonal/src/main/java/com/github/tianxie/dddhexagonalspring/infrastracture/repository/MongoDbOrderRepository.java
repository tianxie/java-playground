package com.github.tianxie.dddhexagonalspring.infrastracture.repository;

import com.github.tianxie.dddhexagonalspring.domain.Order;
import com.github.tianxie.dddhexagonalspring.domain.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class MongoDbOrderRepository implements OrderRepository {

    private final SpringDataOrderRepository orderRepository;

    @Autowired
    public MongoDbOrderRepository(final SpringDataOrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    @Override
    public Optional<Order> findById(UUID id) {
        return orderRepository.findById(id);
    }

    @Override
    public void save(Order order) {
        orderRepository.save(order);
    }
}
