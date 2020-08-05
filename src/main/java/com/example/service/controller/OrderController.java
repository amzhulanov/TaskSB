package com.example.service.controller;

import com.example.service.entities.Order;
import com.example.service.services.ConvertService;
import com.example.service.services.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.PUT;

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

    @RequestMapping(value = "",
            method = RequestMethod.GET,
            headers = "Accept=application/json")
    @ResponseBody
    public String getObject(@RequestBody String json) throws JsonProcessingException {
        Order order = convertService.JsonToOrder(json);
        return orderService.findOrderListByCustomer(order);
    }

    @RequestMapping(value = "",
            method = RequestMethod.PUT,
            headers = "Accept=application/json")
    @ResponseBody
    public String putObjectNew(@RequestBody String json) throws JsonProcessingException {
        Order order = convertService.JsonToOrder(json);
        return orderService.save(order);
    }

}
