package com.alanviera.ecommerce.adapter.out.persistence;

import com.alanviera.ecommerce.adapter.out.persistence.mapper.PriceEntityMapper;
import com.alanviera.ecommerce.adapter.out.persistence.repository.PriceRepository;
import com.alanviera.ecommerce.domain.model.Price;
import com.alanviera.ecommerce.domain.port.out.PriceRepositoryPort;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PriceRepositoryAdapter implements PriceRepositoryPort {
    private final PriceRepository priceRepository;

    public PriceRepositoryAdapter(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Override
    public List<Price> findPricesByDateAndProductAndBrand(LocalDateTime date, Long productId, Long brandId) {
        return priceRepository.findPricesByDateAndProductAndBrand(date, productId, brandId)
                .stream()
                .map(PriceEntityMapper::toDomain)
                .collect(Collectors.toList());
    }
}
