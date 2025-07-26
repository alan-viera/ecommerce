package com.alanviera.ecommerce.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Price {
    private final Long productId;
    private final Long brandId;
    private final Long priceListId;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;
    private final int priority;
    private final BigDecimal price;
    private final String currency;
}
