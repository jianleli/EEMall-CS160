package com.eemall.demo.repository;

import com.eemall.demo.model.customers.Customer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepository extends JpaRepository<Customer, Long> {

    /**
     * Find By Customer Email
     * @param theEmail
     * @return
     */
    Customer findByEmail(String theEmail);

}
