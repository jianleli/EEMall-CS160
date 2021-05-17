package com.eemall.demo.dto;

import com.eemall.demo.model.Order;
import com.eemall.demo.model.OrderItems;
import com.eemall.demo.model.customers.Address;
import com.eemall.demo.model.customers.Customer;
import lombok.Data;

import java.util.Set;

@Data
public class Purchase {
    private Customer customer;
    private Address shipAddress;
    private Address billAddress;
    private Order order;
    private Set<OrderItems> orderItems;
}
