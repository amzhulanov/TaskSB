package com.example.service;

import com.example.service.entities.Order;
import com.example.service.services.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class OrderControllerTest {

    @Autowired
    private MockMvc mvc;

    private OrderService orderService;

    @Autowired
    OrderControllerTest(OrderService orderService) {
        this.orderService = orderService;
    }

    @BeforeEach
    public void setData() throws JsonProcessingException {
        orderService.save(new Order("user1", 23));
    }

    @Test
    void getJsonTest() throws Exception {

        mvc.perform(MockMvcRequestBuilders
                .get("/sb/service")
                .content("{\"customer\":\"user1\",\"cost\":23}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void postJsonTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .put("/sb/service")
                .content("{\"customer\":\"user1\",\"cost\":23}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}