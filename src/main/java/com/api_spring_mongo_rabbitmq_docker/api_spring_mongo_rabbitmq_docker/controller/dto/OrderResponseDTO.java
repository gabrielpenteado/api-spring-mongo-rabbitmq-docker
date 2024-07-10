package com.api_spring_mongo_rabbitmq_docker.api_spring_mongo_rabbitmq_docker.controller.dto;

import java.math.BigDecimal;

import com.api_spring_mongo_rabbitmq_docker.api_spring_mongo_rabbitmq_docker.entity.OrderEntity;

public record OrderResponseDTO(Long orderId,
        Long customerId,
        BigDecimal total) {

    public static OrderResponseDTO fromEntity(OrderEntity entity) {
        return new OrderResponseDTO(entity.getOrderId(), entity.getCustomerId(), entity.getTotal());
    }
}
