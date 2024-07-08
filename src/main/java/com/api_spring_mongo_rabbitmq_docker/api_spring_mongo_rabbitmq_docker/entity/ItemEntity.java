package com.api_spring_mongo_rabbitmq_docker.api_spring_mongo_rabbitmq_docker.entity;

import java.math.BigDecimal;

import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

public class ItemEntity {

    private String product;

    private Integer quantity;

    private BigDecimal price;

    public ItemEntity() {

    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Field(targetType = FieldType.DECIMAL128)
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

}
