package com.thall.wigelltravel.repositories;

import com.thall.wigelltravel.entities.Address;
import com.thall.wigelltravel.entities.Customers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Integer> {

}
