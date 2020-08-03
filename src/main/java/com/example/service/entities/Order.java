package com.example.service.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@NoArgsConstructor
@Data
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue
    protected Long order_id;

    @NotNull
    private String customer;

    @NotNull
    private Integer cost;

    public Order(String customer, Integer cost) {
        this.customer = customer;
        this.cost=cost;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Order order = (Order) o;
        return Objects.equals(order_id, order.order_id) &&
                Objects.equals(customer, order.customer) &&
                Objects.equals(cost, order.cost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(order_id, customer, cost);
    }
}
