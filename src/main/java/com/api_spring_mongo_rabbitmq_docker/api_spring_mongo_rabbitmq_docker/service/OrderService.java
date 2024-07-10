package com.api_spring_mongo_rabbitmq_docker.api_spring_mongo_rabbitmq_docker.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import org.springframework.data.mongodb.core.aggregation.Aggregation.*;

import com.api_spring_mongo_rabbitmq_docker.api_spring_mongo_rabbitmq_docker.controller.dto.OrderResponseDTO;
import com.api_spring_mongo_rabbitmq_docker.api_spring_mongo_rabbitmq_docker.entity.ItemEntity;
import com.api_spring_mongo_rabbitmq_docker.api_spring_mongo_rabbitmq_docker.entity.OrderEntity;
import com.api_spring_mongo_rabbitmq_docker.api_spring_mongo_rabbitmq_docker.listener.dto.OrderCreatedDTO;
import com.api_spring_mongo_rabbitmq_docker.api_spring_mongo_rabbitmq_docker.repository.OrderRepository;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final MongoTemplate mongoTemplate;

    public OrderService(OrderRepository orderRepository, MongoTemplate mongoTemplate) {
        this.orderRepository = orderRepository;
        this.mongoTemplate = mongoTemplate;
    }

    private static List<ItemEntity> getOrderItems(OrderCreatedDTO orderCreatedDTO) {
        return orderCreatedDTO.itens().stream()
                .map(item -> new ItemEntity(item.product(), item.quantity(), item.price()))
                .toList();
    }

    private BigDecimal getTotal(OrderCreatedDTO orderCreatedDTO) {
        return orderCreatedDTO.itens().stream()
                .map(item -> item.price().multiply(BigDecimal.valueOf(item.quantity())))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    public Page<OrderResponseDTO> findAllByCustomerId(Long customerId, PageRequest pageRequest) {
        var orders = orderRepository.findAllByCustomerId(customerId, pageRequest);

        return orders.map(OrderResponseDTO::fromEntity);
    }

    public BigDecimal findTotalOnOrdersByCustomerId(Long customerId) {
        var aggregations = newAggregation(
                match(Criteria.where("customerId").is(customerId)),
                group().sum("total").as("total"));

        var response = mongoTemplate.aggregate(aggregations, "tb_orders", Document.class);

        return new BigDecimal(response.getUniqueMappedResult().get("total").toString());
    }

    public void save(OrderCreatedDTO orderCreatedDTO) {
        var entity = new OrderEntity();

        entity.setOrderId(orderCreatedDTO.codigoPedido());
        entity.setCustomerId(orderCreatedDTO.codigoCliente());
        entity.setTotal(getTotal(orderCreatedDTO));
        entity.setItems(getOrderItems(orderCreatedDTO));

        orderRepository.save(entity);
    }
}
