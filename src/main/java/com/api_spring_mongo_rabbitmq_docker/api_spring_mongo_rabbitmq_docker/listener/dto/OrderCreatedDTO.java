package com.api_spring_mongo_rabbitmq_docker.api_spring_mongo_rabbitmq_docker.listener.dto;

import java.util.List;

public record OrderCreatedDTO(Long codigoPedido,
        Long codigoCliente,
        List<OrderItemDTO> itens) {

}
