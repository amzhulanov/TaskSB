package com.example.service.converter;

import com.example.service.dto.OrderDTO;
import com.example.service.entities.Order;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DTOMapper {

    DTOMapper INSTANCE= Mappers.getMapper(DTOMapper.class);

    @Mapping(target="order_id", ignore = true)
    Order orderFromDTO(OrderDTO orderDTO);

}
