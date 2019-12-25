package com.github.tianxie.dddhexagonalspring.domain;

import java.math.BigDecimal;
import java.util.*;

public class Order {
    private UUID id;
    private OrderStatus status;
    private List<OrderItem> orderItems;
    private BigDecimal price;

    public Order(UUID id, Product product) {
        this.id = id;
        this.orderItems = new ArrayList<>(Arrays.asList(new OrderItem(product)));
        this.status = OrderStatus.CREATED;
        this.price = product.getPrice();
    }

    public void complete() {
        validateState();
        this.status = OrderStatus.COMPLETED;
    }

    public void addOrder(Product product) {
        validateState();
        validateProduct(product);
        orderItems.add(new OrderItem(product));
        price = price.add(product.getPrice());
    }

    public void removeOrder(UUID id) {
        validateState();
        final OrderItem orderItem = getOrderItem(id);
        orderItems.remove(orderItem);

        price = price.subtract(orderItem.getPrice());
    }

    private OrderItem getOrderItem(UUID id) {
        return orderItems
                .stream()
                .filter(orderItem -> orderItem
                        .getProductId()
                        .equals(id))
                .findFirst()
                .orElseThrow(() -> new DomainException("Product with " + id + " doesn't exist."));
    }

    private void validateProduct(Product product) {
        if (product == null) {
            throw new DomainException("The product cannot be null.");
        }
    }

    private void validateState() {
        if (OrderStatus.COMPLETED.equals(status)) {
            throw new DomainException("The order is in completed state.");
        }
    }

    public UUID getId() {
        return id;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public List<OrderItem> getOrderItems() {
        return Collections.unmodifiableList(orderItems);
    }

    public BigDecimal getPrice() {
        return price;
    }

    private Order() {

    }
}
