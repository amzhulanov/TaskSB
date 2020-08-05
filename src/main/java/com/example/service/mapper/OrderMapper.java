package com.example.service.mapper;

import com.example.service.dto.OrderDTO;
import com.example.service.entities.Order;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;


@Mapper
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mapping(source = "customer", target = "customer")
    @Mapping(source = "cost", target = "cost")
    OrderDTO dtoFromOrder(Order order);

}
