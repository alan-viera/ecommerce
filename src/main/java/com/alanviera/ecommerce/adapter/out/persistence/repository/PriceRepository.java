package com.alanviera.ecommerce.adapter.out.persistence.repository;

import com.alanviera.ecommerce.adapter.out.persistence.entity.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceRepository extends JpaRepository<PriceEntity, Long> {
    @Query("SELECT p FROM PriceEntity p " +
            "WHERE p.productId = :productId " +
            "AND p.brandId = :brandId " +
            "AND :date BETWEEN p.startDate AND p.endDate")
    List<PriceEntity> findPricesByDateAndProductAndBrand(
            @Param("date") LocalDateTime date,
            @Param("productId") Long productId,
            @Param("brandId") Long brandId);
}
