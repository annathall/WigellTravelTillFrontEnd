package com.thall.wigelltravel.services;

import com.thall.wigelltravel.entities.User;
import com.thall.wigelltravel.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public interface UserService {

    User findByUsername(String username);
}
