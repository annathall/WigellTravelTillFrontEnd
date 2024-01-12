package com.thall.wigelltravel.services;

import com.thall.wigelltravel.model.CurrencyConversionResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class CurrencyConversionService {
    private RestTemplate restTemplate;

    @Value("${currency-conversion-api-url}")
    private String currencyConversionApiUrl;

    private static final Logger logger = LogManager.getLogger("myLogger");

    public CurrencyConversionService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public double convertSEKToPLN(double sekAmount) {


        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(currencyConversionApiUrl)
                .queryParam("want", "PLN")
                .queryParam("have", "SEK")
                .queryParam("amount", sekAmount);


        ResponseEntity<CurrencyConversionResult> responseEntity = restTemplate.getForEntity(builder.toUriString(), CurrencyConversionResult.class);

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            CurrencyConversionResult result = responseEntity.getBody();
            if (result != null) {
                return result.getNew_amount();
            } else {
                throw new RuntimeException("Error in currency conversion: Response body is null");
            }
        } else {
            throw new RuntimeException("Error in currency conversion: HTTP status code " + responseEntity.getStatusCodeValue());
        }
    }


}
