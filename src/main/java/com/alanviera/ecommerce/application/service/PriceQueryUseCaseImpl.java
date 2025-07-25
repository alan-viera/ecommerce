package com.alanviera.ecommerce.application.service;

import com.alanviera.ecommerce.domain.model.Price;
import com.alanviera.ecommerce.domain.port.in.PriceQueryUseCase;
import com.alanviera.ecommerce.domain.port.out.PriceRepositoryPort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Optional;

@Service
public class PriceQueryUseCaseImpl implements PriceQueryUseCase {
    private final PriceRepositoryPort priceRepositoryPort;

    public PriceQueryUseCaseImpl(PriceRepositoryPort priceRepositoryPort) {
        this.priceRepositoryPort = priceRepositoryPort;
    }

    @Override
    public Optional<Price> getPrice(LocalDateTime date, Long productId, Long brandId) {
        return priceRepositoryPort.findPricesByDateAndProductAndBrand(date, productId, brandId).stream()
                .max(Comparator.comparingInt(Price::getPriority));
    }
}
