package com.example.service.services;

import com.example.service.entities.Order;
import com.example.service.exception.CustomerNotFoundException;
import com.example.service.repository.OrderRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private OrderRepository orderRepository;
    private ConvertService convertService;

    @Autowired
    public OrderService(OrderRepository orderRepository, ConvertService convertService) {
        this.orderRepository = orderRepository;
        this.convertService = convertService;
    }

    //find OrderList => Convert OrderList to Json
    public String findOrderListByCustomer(Order order) throws JsonProcessingException {
        List<Order> orderList=orderRepository.findByCustomer(order.getCustomer()).orElseThrow(()->new CustomerNotFoundException(order.getCustomer()));
        return convertService.OrderListToJSON(orderList);
    }

    //save Order = Convert savedOrder to Json
    public String save(Order order) throws JsonProcessingException {
        Order savedOrder=orderRepository.save(order);
        return convertService.OrderToJSON(savedOrder);
    }

}
