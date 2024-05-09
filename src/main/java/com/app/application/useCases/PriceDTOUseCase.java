package com.app.application.useCases;

import com.app.application.dtos.ArrivingPriceDTO;
import com.app.application.dtos.ExitingPriceDTO;
import com.app.application.mapper.PriceMapper;
import com.app.domain.aggregate.Price;
import com.app.domain.service.PriceService;

public class PriceDTOUseCase {

    private PriceService myService;

    public PriceDTOUseCase(PriceService myService) {
        this.myService = myService;
    }

    public ExitingPriceDTO execute(ArrivingPriceDTO myArrival) {
        Price myPrice = this.myService.searchPrice(myArrival.getPriceDateTime(), myArrival.getProductId(),
                myArrival.getBrandId());

        return PriceMapper.toModel(myPrice);

    }

}
