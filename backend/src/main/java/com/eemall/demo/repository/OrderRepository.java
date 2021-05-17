package com.eemall.demo.repository;

import com.eemall.demo.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface OrderRepository extends JpaRepository<Order, Long> {

    /**
     *
     * @param email
     * @param pageable
     * @return
     */
    Page<Order> findByCustomerEmailOrderByDateCreatedDesc(@Param("email") String email, Pageable pageable);

    /**
     *
     * @param email
     * @return
     */
    List<Order> findByCustomerEmail(@Param("email") String email);

}
