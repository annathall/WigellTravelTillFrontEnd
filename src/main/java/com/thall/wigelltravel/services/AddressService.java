package com.thall.wigelltravel.services;

import com.thall.wigelltravel.entities.Address;
import com.thall.wigelltravel.entities.Customers;

import java.util.List;

public interface AddressService {

    List<Address> findAll();
    Address findById(int id);

    Address save(Address address);

    void deleteById(int id);
}
