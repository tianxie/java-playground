package com.github.tianxie.dddhexagonalspring.domain;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Product {
    private UUID id;
    private BigDecimal price;
    private String name;

    @JsonCreator
    public Product(@JsonProperty("id") final UUID id,
                   @JsonProperty("price") final BigDecimal price,
                   @JsonProperty("name") final String name) {
        this.id = id;
        this.price = price;
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id)
                && Objects.equals(price, product.price)
                && Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, name);
    }
}
