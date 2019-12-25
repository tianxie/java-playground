package com.github.tianxie.dddhexagonalspring.application.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.tianxie.dddhexagonalspring.domain.Product;

import javax.validation.constraints.NotNull;

public class CreateOrderRequest {

    @NotNull
    private Product product;

    @JsonCreator
    public CreateOrderRequest(@JsonProperty("product") @NotNull final Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }
}
