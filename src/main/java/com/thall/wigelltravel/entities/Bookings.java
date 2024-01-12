package com.thall.wigelltravel.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.util.Date;


@Entity
@Table(name = "bookings")
public class Bookings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private int bookingId;

    @Column(name = "departure_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date departureDate;

    @OneToOne
    @JoinColumn(name = "id")
    private Customers customers;

    @OneToOne
    @JoinColumn(name = "dest_id")
    private Destinations destinations;

    public Bookings() {

    }
    public Bookings(int bookingId, Date departureDate, Customers customers, Destinations destinations) {
        this.bookingId = bookingId;
        this.departureDate = departureDate;
        this.customers = customers;
        this.destinations = destinations;
    }

    public Bookings(Date departureDate, Customers customers, Destinations destinations) {
        this.departureDate = departureDate;
        this.customers = customers;
        this.destinations = destinations;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public Customers getCustomers() {
        return customers;
    }

    public void setCustomers(Customers customers) {
        this.customers = customers;
    }

    public Destinations getDestinations() {

        return destinations;
    }

    public void setDestinations(Destinations destinations) {
        this.destinations = destinations;
    }

    @Override
    public String toString() {
        return "Bookings{" +
                "bookingId=" + bookingId +
                ", departureDate=" + departureDate +
                ", customers=" + customers +
                ", destinations=" + destinations +
                '}';
    }
}