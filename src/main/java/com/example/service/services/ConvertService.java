package com.example.service.services;

import com.example.service.converter.DTOMapper;
import com.example.service.converter.OrderMapper;
import com.example.service.dto.OrderDTO;
import com.example.service.entities.Order;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class ConvertService {

    ObjectMapper objectMapper = new ObjectMapper();

//    @Autowired
//    public ConvertService(DTOMapperSpring dtoMapperSpring) {
//    }

    // JSON=>DTO=>Entity
    public Order JsonToOrder(String json) throws JsonProcessingException {
        OrderDTO orderDTO=JsontoDTO(json);
        return DTOMapper.INSTANCE.orderFromDTO(orderDTO);
    }

    //Entity=>ListDTO=>JSON
    public String OrderToJSON(Order order) throws JsonProcessingException {
        List<OrderDTO> orderDTOList = new ArrayList<>();
        OrderDTO orderDTO=OrderMapper.INSTANCE.dtoFromOrder(order);
        orderDTOList.add(orderDTO);
        return DTOToJson(orderDTOList);
    }

    //ListEntity=>ListDTO=>JSON
    public String OrderListToJSON(List<Order> orderList) throws JsonProcessingException {
        List<OrderDTO> orderDTOList = new ArrayList<>();
        orderList.stream().forEach(order -> orderDTOList.add(OrderMapper.INSTANCE.dtoFromOrder(order)));
        return DTOToJson(orderDTOList);
    }

    private String DTOToJson(List<OrderDTO> orderDTOList) throws JsonProcessingException {
        return objectMapper.writeValueAsString(orderDTOList);
    }

    private OrderDTO JsontoDTO(String json) throws JsonProcessingException {
        return objectMapper.readValue(json, OrderDTO.class);
    }

}
