package com.thall.wigelltravel.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.thall.wigelltravel.entities.Bookings;
import com.thall.wigelltravel.entities.Destinations;
import com.thall.wigelltravel.repositories.BookingsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;

@Service
public class BookingsServiceImpl implements BookingsService {

    private BookingsRepository bookingsRepository;
    private CurrencyConversionService currencyConversionService;
    private DestinationsService destinationsService;

    @Autowired
    public BookingsServiceImpl(BookingsRepository bookRepository, CurrencyConversionService currencyConversionService, DestinationsService destinationsService) {
        bookingsRepository = bookRepository;
        this.currencyConversionService = currencyConversionService;
        this.destinationsService = destinationsService;
    }

    @Override
    public List<Bookings> findAll() {
        return bookingsRepository.findAll();
    }

    @Override
    public Bookings findById(int id) {
        Optional<Bookings> b = bookingsRepository.findById(id);
        Bookings bookings = null;
        if (b.isPresent()) {
            bookings = b.get();
        } else {
            throw new RuntimeException("Booking med id: " + id + " hittades inte.");
        }
        return bookings;
    }

    @Transactional
    @Override
    public Bookings save(Bookings bookings) {
        Destinations destination = destinationsService.findById(bookings.getDestinations().getDestId());
        if (destination == null) {
            throw new RuntimeException("Destination not found");
        }
        double convertedPricePLN = currencyConversionService.convertSEKToPLN(bookings.getDestinations().getPricePerWeekSEK());
        bookings.getDestinations().setPricePerWeekPLN(convertedPricePLN);

        return bookingsRepository.save(bookings);
    }


    @Override
    public void deleteById(int id) {
        bookingsRepository.deleteById(id);
    }


    @Override
    public List<Bookings> findBookingsByDestinationId(int destId) {
        return bookingsRepository.findByDestinationsId(destId);
    }
}

