package com.api_spring_mongo_rabbitmq_docker.api_spring_mongo_rabbitmq_docker.listener.dto;

import java.math.BigDecimal;

public record OrderItemDTO(String product,
        Integer quantity,
        BigDecimal price) {

}
