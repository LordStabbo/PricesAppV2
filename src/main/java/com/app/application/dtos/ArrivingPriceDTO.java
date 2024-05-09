package com.app.application.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArrivingPriceDTO {
    private LocalDateTime priceDateTime;
    private String productId;
    private String brandId;

}
