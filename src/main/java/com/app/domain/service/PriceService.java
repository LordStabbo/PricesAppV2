package com.app.domain.service;

import com.app.domain.aggregate.Price;
import com.app.domain.persistance.PriceRepository;
import com.app.domain.util.PriceNotFoundException;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class PriceService {

    private PriceRepository myRepo;

    public PriceService(PriceRepository myRepo) {
        this.myRepo = myRepo;

    }

    public Price searchPrice(LocalDateTime myTime, String productId, String brandId) throws PriceNotFoundException {
        Price myPrice = this.myRepo.findPrices().stream()
                .filter(predicate -> predicate.getStartDate().isBefore(myTime) &&
                        predicate.getEndDate().isAfter(myTime))
                .filter(predicate -> predicate.getProductId().equals(productId))
                .filter(predicate -> predicate.getBrandId().equals(brandId))
                .collect(Collectors.maxBy(Comparator.comparingInt(Price::getPriority)))
                .orElseThrow(() -> new PriceNotFoundException("No Price matching was found"));

        return myPrice;

    }
}
