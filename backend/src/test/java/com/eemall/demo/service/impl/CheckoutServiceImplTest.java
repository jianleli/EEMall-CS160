package com.eemall.demo.service.impl;


import org.junit.Before;
import org.junit.Test;

import org.junit.Rule;
import org.junit.rules.ExpectedException;

import com.eemall.demo.dto.Purchase;
import com.eemall.demo.dto.PurchaseResponse;
import com.eemall.demo.model.Order;
import com.eemall.demo.model.OrderItems;
import com.eemall.demo.model.customers.Customer;
import com.eemall.demo.repository.CustomerRepository;
import com.eemall.demo.repository.OrderRepository;

import static org.mockito.Mockito.when;
import static org.mockito.Matchers.any;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class CheckoutServiceImplTest {
    @Rule
    public ExpectedException thrown= ExpectedException.none();

    @InjectMocks
    CheckoutServiceImpl checkoutService;

    @Mock
    CustomerRepository customerRepository;

    @Mock
    OrderRepository orderRepository;



    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void testPlaceOrderNewCustomer() {
        Purchase purchase = new Purchase();

        Order order = new Order();
        Set<OrderItems> orderItems = new HashSet<>();

        Customer customer = new Customer();
        String email = "i@gmail.com";
        customer.setEmail(email);

        purchase.setOrder(order);
        purchase.setCustomer(customer);
        purchase.setOrderItems(orderItems);

        // no such customer in DB
        List<Customer> customersInDB = new ArrayList<>();
        when(customerRepository.findByEmail(email)).thenReturn(null);
        List<Order> ordersInDB = new ArrayList<>();
        when(customerRepository.save(any(Customer.class))).thenAnswer(new Answer() {

            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Customer c = invocation.getArgument(0);
                customersInDB.add(c);
                for (Order order : c.getOrders()) {
                    ordersInDB.add(order);
                }
                return customersInDB.get(0);
            }

        });

        PurchaseResponse resp = checkoutService.placeOrder(purchase);
        assert(resp.getOrderTrackingNumber().length() > 0);
        assert(customer == customersInDB.get(0));
        assert(ordersInDB.size() == 1);
    }

    @Test
    public void testPlaceOrderOldCustomer() {
        Purchase purchase = new Purchase();

        Order order = new Order();
        Set<OrderItems> orderItems = new HashSet<>();

        Customer customer = new Customer();
        String email = "i@gmail.com";
        customer.setEmail(email);
        customer.setFirstName("JJJ");


        purchase.setOrder(order);
        purchase.setCustomer(customer);
        purchase.setOrderItems(orderItems);

        Set<Order> ordersInDB = new HashSet<>();
        Order orderInDB = new Order();
        ordersInDB.add(orderInDB);

        List<Customer> customersInDB = new ArrayList<>();
        Customer customerInDB = new Customer();
        customerInDB.setEmail(email);
        customerInDB.setFirstName("Jack");
        customerInDB.setOrders(ordersInDB);
        customersInDB.add(customerInDB);


        when(customerRepository.findByEmail(email)).thenReturn(customersInDB.get(0));

        when(customerRepository.save(any(Customer.class))).thenAnswer(new Answer() {

            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Customer c = invocation.getArgument(0);
                customersInDB.set(0, c);
                for (Order order : c.getOrders()) {
                    ordersInDB.add(order);
                }
                return customersInDB.get(0);
            }

        });

        PurchaseResponse resp = checkoutService.placeOrder(purchase);
        assert(resp.getOrderTrackingNumber().length() > 0);
        assert(customerInDB == customersInDB.get(0));
        assert(ordersInDB.size() == 2);

    }

}

