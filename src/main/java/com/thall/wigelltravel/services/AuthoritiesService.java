package com.thall.wigelltravel.services;

import com.thall.wigelltravel.entities.Authorities;
import com.thall.wigelltravel.entities.User;

import java.util.Optional;

public interface AuthoritiesService {
    Optional<Authorities> findByUser(User user);
}
