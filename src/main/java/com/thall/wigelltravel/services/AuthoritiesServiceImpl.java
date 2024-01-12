package com.thall.wigelltravel.services;

import com.thall.wigelltravel.entities.Authorities;
import com.thall.wigelltravel.entities.User;
import com.thall.wigelltravel.repositories.AuthoritiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthoritiesServiceImpl implements AuthoritiesService{

    private final AuthoritiesRepository authoritiesRepository;

    @Autowired
    public AuthoritiesServiceImpl(AuthoritiesRepository authoritiesRepository) {
        this.authoritiesRepository = authoritiesRepository;
    }

    @Override
    public Optional<Authorities> findByUser(User user) {
        return authoritiesRepository.findByUser(user);
    }
}
