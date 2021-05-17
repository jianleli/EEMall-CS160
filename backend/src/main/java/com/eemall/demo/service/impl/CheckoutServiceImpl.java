package com.eemall.demo.service.impl;


import com.eemall.demo.model.Order;

import com.eemall.demo.dto.Purchase;
import com.eemall.demo.dto.PurchaseResponse;
import com.eemall.demo.model.OrderItems;
import com.eemall.demo.model.customers.Customer;
import com.eemall.demo.repository.CustomerRepository;
import com.eemall.demo.service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    @Autowired
    CustomerRepository customerRepository;


    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {

        Order order = purchase.getOrder();
        String orderTrackingNumber = generateOrderTrackingNumber();
        order.setOrderTrackingNumber(orderTrackingNumber);
        Set<OrderItems> orderItems = purchase.getOrderItems();
        orderItems.forEach(item -> order.add(item));
        order.setBillingAddress(purchase.getShipAddress());
        order.setShippingAddress(purchase.getShipAddress());

        Customer customer = purchase.getCustomer();
        String theEmail = customer.getEmail();

        Customer customerFromDB = customerRepository.findByEmail(theEmail);

        if (customerFromDB != null) {
            customer = customerFromDB;
        }

        customer.add(order);
        customerRepository.save(customer);

        return new PurchaseResponse(orderTrackingNumber);
    }


    private String generateOrderTrackingNumber() {
        return UUID.randomUUID().toString();
    }
}









