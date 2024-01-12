package com.thall.wigelltravel.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "destinations")
public class Destinations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dest_id")
    private int destId;
    @Column(name = "country")
    private String country;
    @Column(name = "city")
    private String city;
    @Column(name = "hotel_name")
    private String hotelName;
    @Column(name = "price_per_week_sek")
    private double pricePerWeekSEK;
    @Column(name = "price_per_week_pln")
    private double pricePerWeekPLN;

    public Destinations(){

    }
    public Destinations(int destId, String country, String city, String hotelName, double pricePerWeekSEK, double pricePerWeekPLN) {
        this.destId = destId;
        this.country = country;
        this.city = city;
        this.hotelName = hotelName;
        this.pricePerWeekSEK = pricePerWeekSEK;
        this.pricePerWeekPLN = pricePerWeekPLN;
    }

    public Destinations(String country, String city, String hotelName, double pricePerWeekSEK, double pricePerWeekPLN) {
        this.country = country;
        this.city = city;
        this.hotelName = hotelName;
        this.pricePerWeekSEK = pricePerWeekSEK;
        this.pricePerWeekPLN = pricePerWeekPLN;
    }

    public int getDestId() {
        return destId;
    }

    public void setDestId(int destId) {
        this.destId = destId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public double getPricePerWeekSEK() {
        return pricePerWeekSEK;
    }

    public void setPricePerWeekSEK(double pricePerWeekSEK) {
        this.pricePerWeekSEK = pricePerWeekSEK;
    }

    public double getPricePerWeekPLN() {
        return pricePerWeekPLN;
    }

    public void setPricePerWeekPLN(double pricePerWeekPLN) {
        this.pricePerWeekPLN = pricePerWeekPLN;
    }

    @Override
    public String toString() {
        return "Destinations{" +
                "destId=" + destId +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", hotelName='" + hotelName + '\'' +
                ", pricePerWeekSEK=" + pricePerWeekSEK +
                ", pricePerWeekPLN=" + pricePerWeekPLN +
                '}';
    }
}
