package com.thall.wigelltravel.repositories;

import com.thall.wigelltravel.entities.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomersRepository extends JpaRepository<Customers, Integer> {

    @Query("SELECT c FROM Customers c LEFT JOIN FETCH c.address a LEFT JOIN FETCH c.user u")
    List<Customers> findAllCustomers();
}
