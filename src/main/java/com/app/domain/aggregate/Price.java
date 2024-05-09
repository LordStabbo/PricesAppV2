package com.app.domain.aggregate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.app.domain.vos.AmmountRange;
import com.app.domain.vos.Currency;
import com.app.domain.util.FormatUtil;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Price {

    private String brandId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer priceList;
    private String productId;
    private Integer priority;
    private AmmountRange ammount;
    private Currency currency;

    public String getFinalPrice() {
        return FormatUtil.formatDouble(this.ammount.getValue()) + " " + this.currency.getValue();
    }

}
