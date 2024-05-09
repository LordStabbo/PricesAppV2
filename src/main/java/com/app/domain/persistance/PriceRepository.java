package com.app.domain.persistance;

import com.app.domain.aggregate.Price;

import java.util.List;

public interface PriceRepository {

    List<Price> findPrices();

}
