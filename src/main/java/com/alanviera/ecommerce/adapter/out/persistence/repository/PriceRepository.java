package com.alanviera.ecommerce.adapter.out.persistence.repository;

import com.alanviera.ecommerce.adapter.out.persistence.entity.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepository extends JpaRepository<PriceEntity, Long> {
}
