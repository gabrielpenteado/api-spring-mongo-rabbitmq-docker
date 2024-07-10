package com.api_spring_mongo_rabbitmq_docker.api_spring_mongo_rabbitmq_docker.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import com.api_spring_mongo_rabbitmq_docker.api_spring_mongo_rabbitmq_docker.listener.dto.OrderCreatedDTO;
import com.api_spring_mongo_rabbitmq_docker.api_spring_mongo_rabbitmq_docker.service.OrderService;

import static com.api_spring_mongo_rabbitmq_docker.api_spring_mongo_rabbitmq_docker.config.RabbitMqConfig.ORDER_CREATED_QUEUE;

@Component
public class OrderCreatedListener {

    private final Logger logger = LoggerFactory.getLogger(OrderCreatedListener.class);

    private final OrderService orderService;

    public OrderCreatedListener(OrderService orderService) {
        this.orderService = orderService;
    }

    @RabbitListener(queues = ORDER_CREATED_QUEUE)
    public void listen(Message<OrderCreatedDTO> message) {
        logger.info("Message consumed: {}", message);

        orderService.save(message.getPayload());
    }

}
