package com.thall.wigelltravel.controllers;

import com.thall.wigelltravel.entities.Bookings;
import com.thall.wigelltravel.entities.Customers;
import com.thall.wigelltravel.entities.Destinations;
import com.thall.wigelltravel.services.BookingsService;
import com.thall.wigelltravel.services.CustomersService;
import com.thall.wigelltravel.services.DestinationsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://127.0.0.1:5500", allowCredentials = "true")
public class AdminController {

    @GetMapping("/data")
    public ResponseEntity<String> getData() {
        return ResponseEntity.ok("Hello from the admin endpoint!");
    }


    private static final Logger logger = LogManager.getLogger("myLogger");
    private BookingsService bookingsService;
    private CustomersService customersService;
    private DestinationsService destinationsService;

    public AdminController(BookingsService bookService, DestinationsService destService, CustomersService custService){
        bookingsService = bookService;
        destinationsService = destService;
        customersService = custService;
    }

    @GetMapping("/v1/customers")
    public List<Customers> findAllCustomers(){
        logger.info("Admin lists all customers from the database.");
        return customersService.findAll();
    }
    @GetMapping("/v1/bookings")
    public List<Bookings> findAllBookings(){
        logger.info("Lists all existing bookings.");
        return bookingsService.findAll();
    }
    @GetMapping("/v1/destinations")
    public List<Destinations> findAllDestinations(){
        logger.info("Admin lists all the destinations from the database.");
        return destinationsService.findAll();
    }

    @GetMapping("/v1/customers/{id}")
    public Customers getCustomer(@PathVariable int id){
        Customers customers = customersService.findById(id);
        if(customers == null){
            throw new RuntimeException("Customers with id: " + id + " does not exist.");
        }
        logger.info("Customer with id " + id + " gets fetched from the database.");
        return customers;
    }

    @GetMapping("/v1/destinations/{id}")
    public Destinations getDestination(@PathVariable int id){
        Destinations dest = destinationsService.findById(id);
        if (dest == null){
            throw new RuntimeException("Destination with id: " + id + " does not exist");
        }
        logger.info("Admin fetches a destination from the database");
        return dest;
    }

    @PostMapping("/v1/customers")
    public Customers addCustomer(@RequestBody Customers c){
        c.setId(0);
        Customers customers = customersService.save(c);
        logger.info("Admin adds a new customer.");
        return customers;
    }

    @PostMapping("/v1/destinations")
    public Destinations addDestinations(@RequestBody Destinations d){
        d.setDestId(0);
        Destinations destinations = destinationsService.save(d);
        logger.info("Admin adds destination: " + d);
        return destinations;
    }

    @PutMapping("/v1/customers/{id}")
    public ResponseEntity<Customers> updateCustomer(@PathVariable int id, @RequestBody Customers updatedCustomers){
        Customers existingCustomer = customersService.findById(id);
        if(existingCustomer == null){
            return ResponseEntity.notFound().build();

        }
        existingCustomer.setId(id);
        existingCustomer.setFirstName(updatedCustomers.getFirstName());
        existingCustomer.setLastName(updatedCustomers.getLastName());
        existingCustomer.setAddress(updatedCustomers.getAddress());
        existingCustomer.setUser(updatedCustomers.getUser());
        existingCustomer.setEmail(updatedCustomers.getEmail());
        existingCustomer.setPhone(updatedCustomers.getPhone());
        existingCustomer.setDateOfBirth(updatedCustomers.getDateOfBirth());

        Customers updatedCustomer = customersService.save(existingCustomer);

        logger.info("Admin updates a customer with id: " + id);
        return ResponseEntity.ok(updatedCustomer);
    }

    @PutMapping("/v1/destinations/{id}")
    public ResponseEntity<Destinations>updateDestination(@PathVariable int id, @RequestBody Destinations updatedDestinations){
        Destinations existingDest = destinationsService.findById(id);
        if (existingDest == null) {
            return ResponseEntity.notFound().build();
        }
        existingDest.setDestId(id);
        existingDest.setCountry(updatedDestinations.getCountry());
        existingDest.setCity(updatedDestinations.getCity());
        existingDest.setHotelName(updatedDestinations.getHotelName());
        existingDest.setPricePerWeekSEK(updatedDestinations.getPricePerWeekSEK());
        existingDest.setPricePerWeekPLN(updatedDestinations.getPricePerWeekPLN());

        Destinations updatedDestination = destinationsService.save(existingDest);

        logger.info("Admin updated a destination.");
        return ResponseEntity.ok(updatedDestination);
    }

    @DeleteMapping("/v1/customers/{id}")
    public String deleteCustomer(@PathVariable int id){
        Customers customers = customersService.findById(id);
        if(customers == null){
            throw new RuntimeException("Customer with id: " + id + " does not exist.");
        }
        customersService.deleteById(id);
        logger.info("Admin deletes customer with id: " + id);
        return "Customer with id: " + id + " is deleted.";
    }


    @DeleteMapping("/v1/destinations/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable int id) {

        destinationsService.deleteById(id);
        List<Bookings> affectedBookings = bookingsService.findBookingsByDestinationId(id);
        Destinations replacementDestination = destinationsService.findReplacementDestination();

        for (Bookings booking : affectedBookings) {
            booking.setDestinations(replacementDestination);
            bookingsService.save(booking);
        }
        logger.info("Admin deleted destination: " + id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/v1/findByDestination/{id}")
    public ResponseEntity<List<Bookings>> findBookingsByDestinationId(@PathVariable int destId) {
        List<Bookings> bookings = bookingsService.findBookingsByDestinationId(destId);
        return ResponseEntity.ok(bookings);
    }
}
