package com.thall.wigelltravel.controllers;

import com.thall.wigelltravel.entities.Bookings;
import com.thall.wigelltravel.entities.Customers;
import com.thall.wigelltravel.entities.Destinations;
import com.thall.wigelltravel.services.BookingsService;
import com.thall.wigelltravel.services.CustomersService;
import com.thall.wigelltravel.services.DestinationsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://127.0.0.1:5500", allowCredentials = "true")
public class UserController {

    private BookingsService bookingsService;
    private CustomersService customersService;
    private DestinationsService destinationsService;

    private static final Logger logger = LogManager.getLogger("myLogger");
    public UserController(BookingsService bookService, DestinationsService destService, CustomersService custService){
        bookingsService = bookService;
        destinationsService = destService;
        customersService = custService;
    }

    @GetMapping("/v1/trips")
    public List<Destinations> findAllDestinations(){
        logger.info("User lists all the destinations from the database.");
        return destinationsService.findAll();
    }

    @GetMapping("/v1/bookings/{id}")
    public Bookings getBooking(@PathVariable int id){
        Bookings bookings = bookingsService.findById(id);
        if (bookings == null){
            throw new RuntimeException("Destination with id: " + id + " does not exist");
        }
        logger.info("A booking with id: " + id + " gets fetched from the database.");
        return bookings;
    }

    @PostMapping("/v1/bookings")
    public ResponseEntity<?> addBooking(@RequestBody Bookings b) {
        if (b.getDestinations() == null || b.getCustomers() == null) {

            return ResponseEntity.badRequest().body("Destinations and Customers cannot be null");
        }

        int destinationId = b.getDestinations().getDestId();
        Destinations d = destinationsService.findById(destinationId);

        if (d == null) {
            d = destinationsService.save(b.getDestinations());
        }

        int customerId = b.getCustomers().getId();
        Customers c = customersService.findById(customerId);

        if (c == null) {
            c = customersService.save(b.getCustomers());
        }

        b.setBookingId(0);
        b.setCustomers(c);
        b.setDestinations(d);

        Bookings bookings = bookingsService.save(b);
        logger.info("User adds a new booking");

        return ResponseEntity.ok(bookings);
    }


    @PutMapping("/v1/bookings/{id}")
    public ResponseEntity<Bookings> updateBooking(@PathVariable int id, @RequestBody Bookings updatedBookings) {
        Bookings existingBooking = bookingsService.findById(id);
        if (existingBooking == null) {
            return ResponseEntity.notFound().build();
        }
        existingBooking.setBookingId(id);
        existingBooking.setDepartureDate(updatedBookings.getDepartureDate());
        existingBooking.setCustomers(updatedBookings.getCustomers());
        existingBooking.setDestinations(updatedBookings.getDestinations());

        Bookings updatedBook = bookingsService.save(existingBooking);

        Destinations updatedDestination = destinationsService.findById(updatedBook.getDestinations().getDestId());

        updatedBook.setDestinations(updatedDestination);

        logger.info("User updates a booking.");
        return ResponseEntity.ok(updatedBook);
    }

}
