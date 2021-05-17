package com.eemall.demo.repository;

import com.eemall.demo.model.customers.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RepositoryRestResource(collectionResourceRel = "states", path = "states")
public interface StateRepository extends JpaRepository<State, Integer> {
    /**
     * List State by Country Code
     * @param countryCode
     * @return
     */
    @Query("select s from State s where s.country.code = ?1")
    List<State> findByCountryCode(@Param("code") String countryCode);
}
