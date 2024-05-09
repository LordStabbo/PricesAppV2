package com.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.app.application.dtos.ArrivingPriceDTO;
import com.app.application.dtos.ExitingPriceDTO;
import com.app.application.useCases.PriceDTOUseCase;
import com.app.domain.aggregate.Price;
import com.app.domain.persistance.PriceRepository;
import com.app.domain.service.PriceService;
import com.app.domain.util.FormatUtil;
import com.app.domain.util.PriceNotFoundException;
import com.app.domain.vos.AmmountRange;
import com.app.domain.vos.Currency;

public class PricesApplicationTest {

    private final PriceRepository myPriceRepo = Mockito.mock(PriceRepository.class);
    private final PriceService myService = new PriceService(myPriceRepo);
    private final PriceDTOUseCase myUseCase = new PriceDTOUseCase(myService);

    @Test
    void shouldReturnFirstPrice() {
        // given

        Mockito.when(myPriceRepo.findPrices()).thenReturn(this.gimmePrices());

        ArrivingPriceDTO myArrival = ArrivingPriceDTO.builder()
                .priceDateTime(FormatUtil.dateParser("2020-06-14 10.00.00"))
                .productId("35455")
                .brandId("1")
                .build();

        ExitingPriceDTO res = myUseCase.execute(myArrival);

        assertEquals("35455", res.getProductId());
        assertEquals("1", res.getBrandId());
        assertEquals("2020-06-14 00.00.00", res.getStartDate());
        assertEquals("2020-12-31 23.59.59", res.getEndDate());
        assertEquals("1", res.getRate());
        assertEquals("35,50 EUR", res.getPriceWithCurrency());

    }

    @Test
    void shouldReturnSecondPrice() {
        // given

        Mockito.when(myPriceRepo.findPrices()).thenReturn(this.gimmePrices());

        // when
        ArrivingPriceDTO myArrival = ArrivingPriceDTO.builder()
                .priceDateTime(FormatUtil.dateParser("2020-06-14 16.00.00"))
                .productId("35455")
                .brandId("1")
                .build();

        ExitingPriceDTO res = myUseCase.execute(myArrival);

        // Then
        assertEquals("35455", res.getProductId());
        assertEquals("1", res.getBrandId());
        assertEquals("2020-06-14 15.00.00", res.getStartDate());
        assertEquals("2020-06-14 18.30.00", res.getEndDate());
        assertEquals("2", res.getRate());
        assertEquals("25,45 EUR", res.getPriceWithCurrency());

    }

    @Test
    void shouldReturnThirdPrice() {
        // given

        Mockito.when(myPriceRepo.findPrices()).thenReturn(this.gimmePrices());

        // when
        ArrivingPriceDTO myArrival = ArrivingPriceDTO.builder()
                .priceDateTime(FormatUtil.dateParser("2020-06-14 21.00.00"))
                .productId("35455")
                .brandId("1")
                .build();

        ExitingPriceDTO res = myUseCase.execute(myArrival);

        // Then
        assertEquals("35455", res.getProductId());
        assertEquals("1", res.getBrandId());
        assertEquals("2020-06-14 00.00.00", res.getStartDate());
        assertEquals("2020-12-31 23.59.59", res.getEndDate());
        assertEquals("1", res.getRate());
        assertEquals("35,50 EUR", res.getPriceWithCurrency());

    }

    @Test
    void shouldReturnFourthPrice() {
        // given

        Mockito.when(myPriceRepo.findPrices()).thenReturn(this.gimmePrices());

        // when
        ArrivingPriceDTO myArrival = ArrivingPriceDTO.builder()
                .priceDateTime(FormatUtil.dateParser("2020-06-15 10.00.00"))
                .productId("35455")
                .brandId("1")
                .build();

        ExitingPriceDTO res = myUseCase.execute(myArrival);

        // Then
        assertEquals("35455", res.getProductId());
        assertEquals("1", res.getBrandId());
        assertEquals("2020-06-15 00.00.00", res.getStartDate());
        assertEquals("2020-06-15 11.00.00", res.getEndDate());
        assertEquals("3", res.getRate());
        assertEquals("30,50 EUR", res.getPriceWithCurrency());
    }

    @Test
    void shouldReturnFifthPrice() {
        // given

        Mockito.when(myPriceRepo.findPrices()).thenReturn(this.gimmePrices());

        // when
        ArrivingPriceDTO myArrival = ArrivingPriceDTO.builder()
                .priceDateTime(FormatUtil.dateParser("2020-06-16 21.00.00"))
                .productId("35455")
                .brandId("1")
                .build();

        ExitingPriceDTO res = myUseCase.execute(myArrival);

        // Then
        assertEquals("35455", res.getProductId());
        assertEquals("1", res.getBrandId());
        assertEquals("2020-06-15 16.00.00", res.getStartDate());
        assertEquals("2020-12-30 23.59.59", res.getEndDate());
        assertEquals("4", res.getRate());
        assertEquals("38,95 EUR", res.getPriceWithCurrency());
    }

    @Test
    void whenNoPriceDateTimeFound_shouldReturnError() {
        // given

        Mockito.when(myPriceRepo.findPrices()).thenReturn(this.gimmePrices());

        // when
        ArrivingPriceDTO myArrival = ArrivingPriceDTO.builder()
                .priceDateTime(FormatUtil.dateParser("2025-06-16 21.00.00"))
                .productId("35455")
                .brandId("1")
                .build();

        // Then
        Exception exception = assertThrows(PriceNotFoundException.class, () -> {
            myUseCase.execute(myArrival);
        });

        assertEquals("No Price matching was found", exception.getMessage());
    }

    @Test
    void whenNoPriceProductIdFound_shouldReturnError() {
        // given

        Mockito.when(myPriceRepo.findPrices()).thenReturn(this.gimmePrices());

        // when
        ArrivingPriceDTO myArrival = ArrivingPriceDTO.builder()
                .priceDateTime(FormatUtil.dateParser("2020-06-16 21.00.00"))
                .productId("66466")
                .brandId("1")
                .build();

        // Then
        Exception exception = assertThrows(PriceNotFoundException.class, () -> {
            myUseCase.execute(myArrival);
        });

        assertEquals("No Price matching was found", exception.getMessage());
    }

    @Test
    void whenNoPriceBrandIdFound_shouldReturnError() {
        // given

        Mockito.when(myPriceRepo.findPrices()).thenReturn(this.gimmePrices());

        // when
        ArrivingPriceDTO myArrival = ArrivingPriceDTO.builder()
                .priceDateTime(FormatUtil.dateParser("2020-06-16 21.00.00"))
                .productId("35455")
                .brandId("10")
                .build();

        // Then
        Exception exception = assertThrows(PriceNotFoundException.class, () -> {
            myUseCase.execute(myArrival);
        });

        assertEquals("No Price matching was found", exception.getMessage());
    }

    public List<Price> gimmePrices() {
        List<Price> myPriceList = new ArrayList<>();

        myPriceList.add(Price.builder()
                .productId("35455")
                .brandId("1")
                .ammount(new AmmountRange(35.50))
                .startDate(FormatUtil.dateParser("2020-06-14 00.00.00"))
                .endDate(FormatUtil.dateParser("2020-12-31 23.59.59"))
                .priceList(Integer.valueOf("1"))
                .currency(Currency.valueOf("EUR"))
                .priority(0)
                .build());

        myPriceList.add(Price.builder()
                .productId("35455")
                .brandId("1")
                .ammount(new AmmountRange(25.45))
                .startDate(FormatUtil.dateParser("2020-06-14 15.00.00"))
                .endDate(FormatUtil.dateParser("2020-06-14 18.30.00"))
                .priceList(Integer.valueOf("2"))
                .currency(Currency.valueOf("EUR"))
                .priority(1)
                .build());

        myPriceList.add(Price.builder()
                .productId("35455")
                .brandId("1")
                .ammount(new AmmountRange(30.50))
                .startDate(FormatUtil.dateParser("2020-06-15 00.00.00"))
                .endDate(FormatUtil.dateParser("2020-06-15 11.00.00"))
                .priceList(Integer.valueOf("3"))
                .currency(Currency.valueOf("EUR"))
                .priority(1)
                .build());

        myPriceList.add(Price.builder()
                .productId("35455")
                .brandId("1")
                .ammount(new AmmountRange(38.95))
                .startDate(FormatUtil.dateParser("2020-06-15 16.00.00"))
                .endDate(FormatUtil.dateParser("2020-12-30 23.59.59"))
                .priceList(Integer.valueOf("4"))
                .currency(Currency.valueOf("EUR"))
                .priority(1)
                .build());

        return myPriceList;
    }

}
