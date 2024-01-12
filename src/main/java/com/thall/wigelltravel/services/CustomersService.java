package com.thall.wigelltravel.services;

import com.thall.wigelltravel.entities.Customers;

import java.util.List;

public interface CustomersService {
    List<Customers> findAll();
    Customers findById(int id);
    Customers save(Customers customers);
    void deleteById(int id);
}
