package com.api_spring_mongo_rabbitmq_docker.api_spring_mongo_rabbitmq_docker.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.api_spring_mongo_rabbitmq_docker.api_spring_mongo_rabbitmq_docker.entity.OrderEntity;

public interface OrderRepository extends MongoRepository<OrderEntity, Long> {
    Page<OrderEntity> findAllByCustomerId(Long customerId, PageRequest pageRequest);
}
