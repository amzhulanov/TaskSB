package com.example.service.unit;

import com.example.service.entities.Order;
import com.example.service.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class RepositoryUnitTest {

    private Order order;

    @Autowired
    private OrderRepository orderRepository;

    @BeforeEach
    void setData() {
        order = new Order("user2", 2);
    }

    @Test
    void saveOrderTest() {
        Order savedOrder = orderRepository.save(order);
        assertThat(savedOrder.getCustomer()).isNotNull();
    }

    @Test
    void findCustomerTest() {
        List<Order> orderList = orderRepository.findByCustomer("user2").orElse(null);
        assertThat(orderList == null).isTrue();
    }

}
