package com.eemall.demo.controller;



import com.eemall.demo.model.Order;
import com.eemall.demo.model.User;
import com.eemall.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;


    /**
     * Register a new User
     * @param user
     * @return
     */
    @PostMapping("/register")
    public ResponseEntity<User> save(@RequestBody User user) {
        System.out.print("register:");
        System.out.println(user.getEmail() + ", " + user.getPassword()
                + ", " + user.getName() + ", " + user.getPhone() + ", " + user.getAddress());
        try {
            return ResponseEntity.ok(userService.save(user));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Update a user profile
     * @param user
     * @return
     */
    @PutMapping("/profile")
    public ResponseEntity<User> update(@RequestBody User user) {
        try {
            User userUpdated = userService.update(user);
            return ResponseEntity.ok(userUpdated);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Get User profile
     * @param email
     * @param principal
     * @return
     */
    @GetMapping("/profile/{email}")
    public ResponseEntity<User> getProfile(@PathVariable("email") String email, Principal principal) {
        User user = userService.findByEmail(email);
        if (user == null) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok(userService.findByEmail(email));
        }

    }

    /**
     * Get User Order History
     * @param email
     * @return
     */
    @GetMapping("/orders/{email}")
    public ResponseEntity<List<Order>> getOrders(@PathVariable("email") String email) {
        List<Order> orders = userService.getOrders(email);
        if (orders == null || orders.size() == 0) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok(userService.getOrders(email));
        }
    }

}
