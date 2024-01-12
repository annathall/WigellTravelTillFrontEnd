package com.thall.wigelltravel.services;

import com.thall.wigelltravel.entities.Address;
import com.thall.wigelltravel.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService{

    private AddressRepository addressRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addRepository){
        addressRepository = addRepository;
    }
    @Override
    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    @Override
    public Address findById(int id) {
        return null;
    }

    @Override
    public Address save(Address address) {
        return null;
    }

    @Override
    public void deleteById(int id) {

    }
}
