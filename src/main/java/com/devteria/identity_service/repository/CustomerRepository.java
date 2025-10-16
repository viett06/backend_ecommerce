package com.devteria.identity_service.repository;

import com.devteria.identity_service.entity.Customer;
import com.devteria.identity_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
    // JPQL query theo đúng field trong entity Customer
    @Query("SELECT c FROM Customer c WHERE c.firstName = :firstName")
    List<Customer> findByFirstName(@Param("firstName") String firstName);


    @Query("SELECT c FROM Customer c WHERE c.user = :user")
    Optional<Customer> findByUser(@Param("user") User user);


}
