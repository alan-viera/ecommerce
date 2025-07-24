package com.alanviera.ecommerce.domain.port.out;

import com.alanviera.ecommerce.domain.model.Price;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceRepositoryPort {
    List<Price> findPricesByDateAndProductAndBrand(LocalDateTime date, Long productId, Long brandId);
}
