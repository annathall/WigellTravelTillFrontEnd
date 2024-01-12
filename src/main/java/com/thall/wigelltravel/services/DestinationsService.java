package com.thall.wigelltravel.services;

import com.thall.wigelltravel.entities.Destinations;

import java.util.List;
import java.util.Optional;

public interface DestinationsService {
    List<Destinations> findAll();
    Destinations findById(int destId);
    Destinations save(Destinations destinations);
    void deleteById(int destId);

    Destinations findReplacementDestination();

    void deleteDestinations(int id);
}
