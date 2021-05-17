package com.eemall.demo.repository;

import com.eemall.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface UserRepository extends JpaRepository<User, String> {

    /**
     * Find a User By Email
     * @param email
     * @return
     */
    User findByEmail(String email);

    /**
     * Find User By Role
     * @param role
     * @return
     */
    Collection<User> findAllByRole(String role);
}
