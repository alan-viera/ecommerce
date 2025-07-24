package com.alanviera.ecommerce.application.service;

import com.alanviera.ecommerce.domain.model.Price;
import com.alanviera.ecommerce.domain.port.in.PriceQueryUseCase;

import java.time.LocalDateTime;
import java.util.Optional;

public class PriceQueryUseCaseImpl implements PriceQueryUseCase {
    @Override
    public Optional<Price> getPrice(LocalDateTime date, Long productId, Long brandId) {
        return Optional.empty();
    }
}
