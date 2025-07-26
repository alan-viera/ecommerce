package com.alanviera.ecommerce.adapter.out.persistence;

import com.alanviera.ecommerce.adapter.out.persistence.entity.PriceEntity;
import com.alanviera.ecommerce.adapter.out.persistence.repository.PriceRepository;
import com.alanviera.ecommerce.domain.model.Price;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
@Import(PriceRepositoryAdapter.class)
public class PriceRepositoryAdapterTest {
    @Autowired
    private PriceRepository priceRepository;

    @Autowired
    private PriceRepositoryAdapter priceRepositoryAdapter;

    @Test
    @DisplayName("Should return matching price entity between date range")
    void findPricesByDateAndProductAndBrand_matching() {
        Long productId = 35455L;
        Long brandId = 1L;
        BigDecimal priceValue = new BigDecimal("35.50");

        // Given
        PriceEntity price1 = new PriceEntity();
        price1.setBrandId(brandId);
        price1.setStartDate(LocalDateTime.of(2020, 1, 1, 10, 0));
        price1.setEndDate(LocalDateTime.of(2020, 1, 10, 10, 0));
        price1.setPriceListId(1L);
        price1.setProductId(productId);
        price1.setPriority(1);
        price1.setPrice(priceValue);
        price1.setCurrency("EUR");
        priceRepository.save(price1);

        PriceEntity price2 = new PriceEntity();
        price2.setBrandId(brandId);
        price2.setStartDate(LocalDateTime.of(2020, 1, 11, 10, 0));
        price2.setEndDate(LocalDateTime.of(2020, 1, 20, 10, 0));
        price2.setPriceListId(2L);
        price2.setProductId(productId);
        price2.setPriority(1);
        price2.setPrice(new BigDecimal("99.50"));
        price2.setCurrency("EUR");
        priceRepository.save(price2);

        LocalDateTime queryDate = LocalDateTime.of(2020, 1, 5, 11, 0);

        // When
        List<Price> results = priceRepositoryAdapter.findPricesByDateAndProductAndBrand(
                queryDate, productId, brandId);

        // Then
        assertThat(results).hasSize(1);
        assertThat(results.get(0).getPrice()).isEqualByComparingTo(priceValue);
    }

    @Test
    @DisplayName("Should return empty list when no price matches")
    void findPricesByDateAndProductAndBrand_noResult() {
        // Given
        LocalDateTime queryDate = LocalDateTime.of(2020, 6, 14, 10, 0);

        // When
        List<Price> results = priceRepositoryAdapter.findPricesByDateAndProductAndBrand(
                queryDate, 9999L, 99L);

        // Then
        assertThat(results).isEmpty();
    }
}
