package com.eemall.demo.service.impl;

import com.eemall.demo.model.Order;
import com.eemall.demo.model.User;
import com.eemall.demo.model.shoppingCart.Cart;
import com.eemall.demo.repository.CartRepository;
import com.eemall.demo.repository.OrderRepository;
import com.eemall.demo.repository.UserRepository;
import com.eemall.demo.service.UserService;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
@DependsOn("passwordEncoder")
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Collection<User> findAllByRole(String roll) {
        return userRepository.findAllByRole(roll);
    }

    @SneakyThrows
    @Override
    @Transactional
    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        try {
            User savedUser = userRepository.save(user);
            Cart savedCart = cartRepository.save(new Cart(savedUser));
            savedUser.setCart(savedCart);
            return userRepository.save(savedUser);

        } catch (Exception e) {
            throw new Exception("Cant Find User");
        }
    }

    /**
     * Update User Info
     * @param user
     * @return
     */
    @Override
    @Transactional
    public User update(User user) {
        User currentUser = userRepository.findByEmail(user.getEmail());
        currentUser.setName(user.getName());
        currentUser.setAddress(user.getAddress());
        currentUser.setPhone(user.getPhone());
        return userRepository.save(currentUser);
    }

    /**
     * Get User's Order History
     * @param email
     * @return
     */
    @Override
    public List<Order> getOrders(String email) {
        List<Order> orders = orderRepository.findByCustomerEmail(email);
        return orders;
    }

    /**
     *
     * @param email
     * @param pageable
     * @return
     */
    @Override
    public Page<Order> getOrders(String email, Pageable pageable) {
        Page<Order> pageOrders =  orderRepository.findByCustomerEmailOrderByDateCreatedDesc(email, pageable);
        return pageOrders;
    }

}
