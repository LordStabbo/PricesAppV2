package com.app.application.mapper;

import com.app.application.dtos.ExitingPriceDTO;
import com.app.domain.aggregate.Price;
import com.app.domain.vos.AmmountRange;
import com.app.domain.vos.Currency;
import com.app.domain.util.FormatUtil;
import com.app.infrastructure.dataBase.PriceDTO;

public class PriceMapper {

    private PriceMapper() {

    }

    public static Price toModel(PriceDTO myPrice) {
        return Price.builder()
                .productId(myPrice.getProductId())
                .brandId(myPrice.getBrandId())
                .ammount(new AmmountRange(myPrice.getPrice()))
                .startDate(myPrice.getStartDate())
                .endDate(myPrice.getEndDate())
                .priceList(Integer.valueOf(myPrice.getPriceList()))
                .currency(Currency.valueOf(myPrice.getCurrency()))
                .priority(myPrice.getPriority())
                .build();
    }

    public static ExitingPriceDTO toModel(Price myPrice) {

        return ExitingPriceDTO.builder()
                .productId(myPrice.getProductId())
                .brandId(myPrice.getBrandId())
                .startDate(FormatUtil.toFormat(myPrice.getStartDate()))
                .endDate(FormatUtil.toFormat(myPrice.getEndDate()))
                .priceWithCurrency(myPrice.getFinalPrice())
                .rate(myPrice.getPriceList().toString())
                .build();
    }
}
