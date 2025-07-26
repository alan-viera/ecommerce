package com.alanviera.ecommerce.adapter.out.persistence;

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
        // Given
        LocalDateTime queryDate = LocalDateTime.of(2020, 6, 14, 10, 0);
        Long productId = 35455L;
        Long brandId = 1L;

        // When
        List<Price> results = priceRepositoryAdapter.findPricesByDateAndProductAndBrand(
                queryDate, productId, brandId);

        // Then
        assertThat(results).hasSize(1);
        assertThat(results.get(0).getPrice()).isEqualByComparingTo(new BigDecimal("35.50"));
        assertThat(results.get(0).getPriceListId()).isEqualTo(1L);
    }

    @Test
    @DisplayName("Should return empty list when no price matches")
    void findPricesByDateAndProductAndBrand_noResult() {
        // Given
        LocalDateTime queryDate = LocalDateTime.of(2020, 6, 14, 10, 0);
        Long productId = 9999L;
        Long brandId = 99L;

        // When
        List<Price> results = priceRepositoryAdapter.findPricesByDateAndProductAndBrand(
                queryDate, productId, brandId);

        // Then
        assertThat(results).isEmpty();
    }
}
