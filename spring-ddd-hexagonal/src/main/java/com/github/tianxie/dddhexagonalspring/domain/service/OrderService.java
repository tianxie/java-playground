package com.github.tianxie.dddhexagonalspring.domain.service;

import com.github.tianxie.dddhexagonalspring.domain.Product;

import java.util.UUID;

public interface OrderService {
    UUID createOrder(Product product);

    void addProduct(UUID id, Product product);

    void completeOrder(UUID id);

    void deleteProduct(UUID id, UUID productId);
}
