package com.api_spring_mongo_rabbitmq_docker.api_spring_mongo_rabbitmq_docker.controller.dto;

import org.springframework.data.domain.Page;

public record PaginationResponseDTO(Integer page,
        Integer pageSize,
        Long totalElements,
        Integer totalPages) {

    public static PaginationResponseDTO fromPage(Page<?> page) {
        return new PaginationResponseDTO(
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages());
    }
}