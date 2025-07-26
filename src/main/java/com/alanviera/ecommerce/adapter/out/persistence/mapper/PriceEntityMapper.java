package com.alanviera.ecommerce.adapter.out.persistence.mapper;

import com.alanviera.ecommerce.adapter.out.persistence.entity.PriceEntity;
import com.alanviera.ecommerce.domain.model.Price;

public class PriceEntityMapper {
    public static Price toDomain(PriceEntity entity) {
        return new Price(
                entity.getProductId(),
                entity.getBrandId(),
                entity.getPriceListId(),
                entity.getStartDate(),
                entity.getEndDate(),
                entity.getPriority(),
                entity.getPrice(),
                entity.getCurrency()
        );
    }
}
