package com.example.service;

import com.example.service.entities.Order;
import com.example.service.dto.OrderDTO;
import com.example.service.services.ConvertService;
import com.example.service.services.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class OrderControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    //@Autowired
    private OrderService orderService;
    private ConvertService convertService;


    @BeforeEach
    public void setData() throws JsonProcessingException {
        orderService.save(new Order ("user1", 23));
    }

//    @Test
//    void getObject() throws Exception {
////        OrderDTO orderDTO=  new OrderDTO("user1",123);
////        String json = null;
////        given(orderService.findByCustomer(orderDTO)).willReturn(json);
//
//        mvc.perform(get("/sb/service")
//                .content("{\n" +
//                        "   \"customer\": \"user1\"\n" +
//                        "}")
//        .contentType(MediaType.APPLICATION_JSON))
//
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$",hasSize(3)));
//    }

    //   @Test
//    void putObject() throws Exception {
//        mvc.perform(put("/sb/service")
//                //.content("{\"customer\": \"user1\",\"cost\": \"333\"}")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.content().string("{\"customer\": \"user1\",\"cost\": \"333\"}"));
//                //.andExpect(jsonPath("$",hasSize(3)));
//    }
    @Test
    void getJsonTest() throws Exception {
        List<Order> orderList = new ArrayList<>();
        orderList.add(new Order("user1", 23));
        BDDMockito.given(orderService.findOrderListByCustomer(orderList.get(0))).willReturn(convertService.OrderListToJSON(orderList));

        mvc.perform(MockMvcRequestBuilders
                .get("/sb/service")
                .content("{\"customer\": \"user1\",\"cost\": \"23\"}"))

                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}