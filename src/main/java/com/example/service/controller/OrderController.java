package com.example.service.controller;

import com.example.service.entities.Order;
import com.example.service.services.ConvertService;
import com.example.service.services.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/service")
@Slf4j
public class OrderController {
    private final OrderService orderService;
    private final ConvertService convertService;

    @Autowired
    public OrderController(OrderService orderService, ConvertService convertService) {
        this.orderService = orderService;
        this.convertService = convertService;
    }


    @GetMapping("")
    ResponseEntity<String> getObject(@RequestBody String json) throws JsonProcessingException {
        Order order=convertService.JsonToOrder(json);
        return new ResponseEntity<>(orderService.findOrderListByCustomer(order), HttpStatus.OK);
    }

    @PutMapping("")
    ResponseEntity<String>  putObject(@RequestBody String json) throws JsonProcessingException {
        Order order= convertService.JsonToOrder(json);
        return new ResponseEntity<>(orderService.save(order),HttpStatus.OK);
    }

}
