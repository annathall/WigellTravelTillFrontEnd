package com.thall.wigelltravel.repositories;

import com.thall.wigelltravel.entities.Authorities;
import com.thall.wigelltravel.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthoritiesRepository extends JpaRepository<Authorities, Long> {
    Optional<Authorities> findByUser(User user);
}
