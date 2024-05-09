package com.app.infrastructure.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.application.dtos.ArrivingPriceDTO;
import com.app.application.dtos.ExitingPriceDTO;
import com.app.application.useCases.PriceDTOUseCase;
import com.app.domain.util.FormatUtil;
import com.app.domain.util.PriceNotFoundException;

@RestController
public class PricesController {

    @Autowired
    PriceDTOUseCase myUseCases;

    @GetMapping("/price/finder")
    public ResponseEntity<?> checkMyPrice(
            @RequestParam(required = true) String date,
            @RequestParam(required = true) String productId,
            @RequestParam(required = true) String brandId)
            throws PriceNotFoundException {

        ArrivingPriceDTO myArrival = ArrivingPriceDTO.builder()
                .priceDateTime(FormatUtil.dateParser(date))
                .productId(productId)
                .brandId(brandId)
                .build();

        ExitingPriceDTO myExit = myUseCases.execute(myArrival);
        return ResponseEntity.status(HttpStatus.OK).body(myExit);

    }

}
