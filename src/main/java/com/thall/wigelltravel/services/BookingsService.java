package com.thall.wigelltravel.services;

import com.thall.wigelltravel.entities.Bookings;
import com.thall.wigelltravel.repositories.BookingsRepository;

import java.util.List;

public interface BookingsService {
    List<Bookings> findAll();
    Bookings findById(int id);
    Bookings save(Bookings bookings);
    void deleteById(int id);

    List<Bookings> findBookingsByDestinationId(int destId);

}
