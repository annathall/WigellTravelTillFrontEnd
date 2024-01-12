package com.thall.wigelltravel.repositories;

import com.thall.wigelltravel.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
