package com.eemall.demo.service;

import com.eemall.demo.model.Order;
import com.eemall.demo.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;

public interface UserService {

    /**
     *  Findy By Email
     * @param Email
     * @return
     */
    User findByEmail(String Email);

    /**
     * Find All By Role
     * @param roll
     * @return
     */
    Collection<User> findAllByRole(String roll);

    /**
     * Save Function
     * @param user
     * @return
     */
    User save(User user);

    /**
     * Update Function
     * @param user
     * @return
     */
    User update(User user);

    /**
     *
     * @param email
     * @return
     */
    List<Order> getOrders(String email);

    /**
     *
     * @param email
     * @param pageable
     * @return
     */
    Page<Order> getOrders(String email, Pageable pageable);
}
