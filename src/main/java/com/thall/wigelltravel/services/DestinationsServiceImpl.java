package com.thall.wigelltravel.services;

import com.thall.wigelltravel.entities.Destinations;
import com.thall.wigelltravel.repositories.DestinationsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class DestinationsServiceImpl implements DestinationsService{
    private DestinationsRepository destinationsRepository;
    private CurrencyConversionService currencyConversionService;

    @Autowired
    public DestinationsServiceImpl(DestinationsRepository destRepository, CurrencyConversionService currencyConversionService){
        destinationsRepository = destRepository;
        this.currencyConversionService = currencyConversionService;
    }
    @Override
    public List<Destinations> findAll() {
        return destinationsRepository.findAll();
    }


    @Override
    public Destinations findById(int destId) {
        Optional<Destinations> d = destinationsRepository.findById(destId);
        Destinations destinations = null;
        if(d.isPresent()){
            destinations = d.get();
        }
        else{
            throw new RuntimeException("Destination med id: " + destId + " finns inte");
        }
        return destinations;
    }

    @Transactional
    @Override
    public Destinations save(Destinations destinations) {
        double convertedPricePLN = currencyConversionService.convertSEKToPLN(destinations.getPricePerWeekSEK());
        destinations.setPricePerWeekPLN( convertedPricePLN);

        return destinationsRepository.save(destinations);
    }

    @Transactional
    @Override
    public void deleteById(int destId) {
        destinationsRepository.deleteById(destId);
    }


    @Override
    public Destinations findReplacementDestination() {
        return destinationsRepository.findFirstByOrderByDestIdAsc().orElse(null);
    }

    @Override
    public void deleteDestinations(int id) {

    }
}
