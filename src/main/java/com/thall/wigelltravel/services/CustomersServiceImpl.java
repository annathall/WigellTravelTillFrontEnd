package com.thall.wigelltravel.services;

import com.thall.wigelltravel.entities.Customers;
import com.thall.wigelltravel.repositories.CustomersRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomersServiceImpl implements CustomersService{
    private CustomersRepository customersRepository;
    @Autowired
    public CustomersServiceImpl(CustomersRepository custRepository){
        customersRepository = custRepository;
    }
    @Override
    public List<Customers> findAll() {
        return customersRepository.findAll();
    }

    @Override
    public Customers findById(int id) {
        Optional<Customers> c = customersRepository.findById(id);
        Customers customers = null;
        if(c.isPresent()){
            customers = c.get();
        }
        else{
            throw new RuntimeException("Customer med id: " + id + " hittades inte.");
        }
        return customers;
    }

    @Transactional
    @Override
    public Customers save(Customers customers) {
        return customersRepository.save(customers);
    }

    @Transactional
    @Override
    public void deleteById(int id) {
        customersRepository.deleteById(id);

    }
}
