package com.app.application.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExitingPriceDTO {

    private String productId;
    private String brandId;
    private String rate;
    private String startDate;
    private String endDate;
    private String priceWithCurrency;

}
