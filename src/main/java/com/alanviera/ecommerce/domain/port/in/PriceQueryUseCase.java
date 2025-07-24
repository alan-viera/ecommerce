package com.alanviera.ecommerce.domain.port.in;

import com.alanviera.ecommerce.domain.model.Price;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceQueryUseCase {
    Optional<Price> getPrice(LocalDateTime date, Long productId, Long brandId);
}
