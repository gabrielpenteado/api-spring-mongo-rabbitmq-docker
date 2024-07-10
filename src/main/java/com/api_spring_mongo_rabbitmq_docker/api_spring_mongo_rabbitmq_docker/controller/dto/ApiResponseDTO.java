package com.api_spring_mongo_rabbitmq_docker.api_spring_mongo_rabbitmq_docker.controller.dto;

import java.util.List;
import java.util.Map;

public record ApiResponseDTO<T>(Map<String, Object> summary,
        List<T> data,
        PaginationResponseDTO pagination) {
}
