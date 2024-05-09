package com.app.infrastructure.controller;

import com.app.PricesApplication;
import com.app.application.dtos.ExitingPriceDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = PricesApplication.class)
class PricesControllerTest {

    @Autowired
    private TestRestTemplate myTemplate;

    @Test
    void shouldWorksFirstCase() {

        ResponseEntity<ExitingPriceDTO> myResponse = myTemplate.getForEntity(
                "/price/finder?date=2020-06-14 10.00.00&productId=35455&brandId=1",
                ExitingPriceDTO.class);

        assertTrue(myResponse.getStatusCode().is2xxSuccessful());

        ExitingPriceDTO res = myResponse.getBody();

        assertEquals("35455", res.getProductId());
        assertEquals("1", res.getBrandId());
        assertEquals("2020-06-14 00.00.00", res.getStartDate());
        assertEquals("2020-12-31 23.59.59", res.getEndDate());
        assertEquals("1", res.getRate());
        assertEquals("35,50 EUR", res.getPriceWithCurrency());
    }

    @Test
    void shouldWorksSecondCase() {

        ResponseEntity<ExitingPriceDTO> myResponse = myTemplate.getForEntity(
                "/price/finder?date=2020-06-14 16.00.00&productId=35455&brandId=1",
                ExitingPriceDTO.class);

        assertTrue(myResponse.getStatusCode().is2xxSuccessful());

        ExitingPriceDTO res = myResponse.getBody();

        assertEquals("35455", res.getProductId());
        assertEquals("1", res.getBrandId());
        assertEquals("2020-06-14 15.00.00", res.getStartDate());
        assertEquals("2020-06-14 18.30.00", res.getEndDate());
        assertEquals("2", res.getRate());
        assertEquals("25,45 EUR", res.getPriceWithCurrency());
    }

    @Test
    void shouldWorksThirdCase() {

        ResponseEntity<ExitingPriceDTO> myResponse = myTemplate.getForEntity(
                "/price/finder?date=2020-06-14 21.00.00&productId=35455&brandId=1",
                ExitingPriceDTO.class);

        assertTrue(myResponse.getStatusCode().is2xxSuccessful());

        ExitingPriceDTO res = myResponse.getBody();

        assertEquals("35455", res.getProductId());
        assertEquals("1", res.getBrandId());
        assertEquals("2020-06-14 00.00.00", res.getStartDate());
        assertEquals("2020-12-31 23.59.59", res.getEndDate());
        assertEquals("1", res.getRate());
        assertEquals("35,50 EUR", res.getPriceWithCurrency());
    }

    @Test
    void shouldWorksFourthCase() {

        ResponseEntity<ExitingPriceDTO> myResponse = myTemplate.getForEntity(
                "/price/finder?date=2020-06-15 10.00.00&productId=35455&brandId=1",
                ExitingPriceDTO.class);

        assertTrue(myResponse.getStatusCode().is2xxSuccessful());

        ExitingPriceDTO res = myResponse.getBody();

        assertEquals("35455", res.getProductId());
        assertEquals("1", res.getBrandId());
        assertEquals("2020-06-15 00.00.00", res.getStartDate());
        assertEquals("2020-06-15 11.00.00", res.getEndDate());
        assertEquals("3", res.getRate());
        assertEquals("30,50 EUR", res.getPriceWithCurrency());
    }

    @Test
    void shouldWorksFifthCase() {

        ResponseEntity<ExitingPriceDTO> myResponse = myTemplate.getForEntity(
                "/price/finder?date=2020-06-15 10.00.00&productId=35455&brandId=1",
                ExitingPriceDTO.class);

        assertTrue(myResponse.getStatusCode().is2xxSuccessful());

        ExitingPriceDTO res = myResponse.getBody();

        assertEquals("35455", res.getProductId());
        assertEquals("1", res.getBrandId());
        assertEquals("2020-06-15 00.00.00", res.getStartDate());
        assertEquals("2020-06-15 11.00.00", res.getEndDate());
        assertEquals("3", res.getRate());
        assertEquals("30,50 EUR", res.getPriceWithCurrency());
    }

    @Test
    void whenNoPriceDateFound_shouldReturnError() {
        ResponseEntity<String> myResponse = myTemplate.getForEntity(
                "/price/finder?date=2025-06-15 10.00.00&productId=35455&brandId=1",
                String.class);

        assertTrue(myResponse.getStatusCode().is4xxClientError());
    }

    @Test
    void whenNoPriceProductIdFound_shouldReturnError() {
        ResponseEntity<String> myResponse = myTemplate.getForEntity(
                "/price/finder?date=2020-06-15 10.00.00&productId=36466&brandId=1",
                String.class);

        assertTrue(myResponse.getStatusCode().is4xxClientError());
        assertEquals("ERROR: No Price was found", myResponse.getBody());
    }

    @Test
    void whenNoPriceBrandIdFound_shouldReturnError() {
        ResponseEntity<String> myResponse = myTemplate.getForEntity(
                "/price/finder?date=2020-06-15 10.00.00&productId=35455&brandId=10",
                String.class);

        assertTrue(myResponse.getStatusCode().is4xxClientError());
        assertEquals("ERROR: No Price was found", myResponse.getBody());
    }

    @Test
    void whenIllegalDate_shouldReturnError() {
        ResponseEntity<String> myResponse = myTemplate.getForEntity(
                "/price/finder?date=2020-06-15-10.00.61&productId=35455&brandId=1",
                String.class);

        assertTrue(myResponse.getStatusCode().is5xxServerError());
    }
}
