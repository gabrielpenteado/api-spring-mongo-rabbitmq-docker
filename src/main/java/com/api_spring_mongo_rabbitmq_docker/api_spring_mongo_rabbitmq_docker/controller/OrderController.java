package com.api_spring_mongo_rabbitmq_docker.api_spring_mongo_rabbitmq_docker.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api_spring_mongo_rabbitmq_docker.api_spring_mongo_rabbitmq_docker.controller.dto.ApiResponseDTO;
import com.api_spring_mongo_rabbitmq_docker.api_spring_mongo_rabbitmq_docker.controller.dto.OrderResponseDTO;
import com.api_spring_mongo_rabbitmq_docker.api_spring_mongo_rabbitmq_docker.controller.dto.PaginationResponseDTO;
import com.api_spring_mongo_rabbitmq_docker.api_spring_mongo_rabbitmq_docker.service.OrderService;

import java.util.Map;

@RestController
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/customers/{customerId}/orders")
    public ResponseEntity<ApiResponseDTO<OrderResponseDTO>> listOrders(@PathVariable("customerId") Long customerId,
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {

        var pageResponse = orderService.findAllByCustomerId(customerId, PageRequest.of(page, pageSize));
        var totalOnOrders = orderService.findTotalOnOrdersByCustomerId(customerId);

        return ResponseEntity.ok(new ApiResponseDTO<>(
                Map.of("totalOnOrders", totalOnOrders),
                pageResponse.getContent(),
                PaginationResponseDTO.fromPage(pageResponse)));
    }

}
