package com.thall.wigelltravel.repositories;

import com.thall.wigelltravel.entities.Destinations;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DestinationsRepository extends JpaRepository<Destinations, Integer> {
    Optional<Destinations> findFirstByOrderByDestIdAsc();
}

