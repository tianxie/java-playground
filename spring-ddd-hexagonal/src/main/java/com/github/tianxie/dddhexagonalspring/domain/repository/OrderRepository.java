package com.github.tianxie.dddhexagonalspring.domain.repository;

import com.github.tianxie.dddhexagonalspring.domain.Order;

import java.util.Optional;
import java.util.UUID;

public interface OrderRepository {
    Optional<Order> findById(UUID id);

    void save(Order order);
}
