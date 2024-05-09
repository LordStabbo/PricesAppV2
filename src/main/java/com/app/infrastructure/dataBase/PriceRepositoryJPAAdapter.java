package com.app.infrastructure.dataBase;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.application.mapper.PriceMapper;
import com.app.domain.aggregate.Price;
import com.app.domain.persistance.PriceRepository;

@Repository
public class PriceRepositoryJPAAdapter implements PriceRepository {

    @Autowired
    private PriceRepositoryJPA myPriceRepo;

    @Override
    public List<Price> findPrices() {
        return this.myPriceRepo.findAll()
                .stream()
                .map(PriceMapper::toModel)
                .collect(Collectors.toList());
    }

}
