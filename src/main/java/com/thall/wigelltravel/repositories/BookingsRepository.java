package com.thall.wigelltravel.repositories;

import com.thall.wigelltravel.entities.Bookings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookingsRepository extends JpaRepository<Bookings, Integer> {
    @Query("SELECT b FROM Bookings b WHERE b.destinations.destId = :destId")
    List<Bookings> findByDestinationsId(@Param("destId") int destId);
}
