package com.alanviera.ecommerce.application.service;

import com.alanviera.ecommerce.domain.model.Price;
import com.alanviera.ecommerce.domain.port.out.PriceRepositoryPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PriceQueryUseCaseImplTest {
    @Mock
    private PriceRepositoryPort priceRepositoryPort;

    @InjectMocks
    private PriceQueryUseCaseImpl priceQueryUseCase;

    @DisplayName("No prices in repository -> Return empty result")
    @Test
    void getPrice_noPrices_noResult() {
        LocalDateTime now = LocalDateTime.now();

        List<Price> prices = Collections.emptyList();

        when(priceRepositoryPort.findPricesByDateAndProductAndBrand(now, 1L, 1L)).thenReturn(prices);

        Optional<Price> result = priceQueryUseCase.getPrice(now, 1L, 1L);

        assertTrue(result.isEmpty());
    }

    @DisplayName("Single price in repository -> Return that price")
    @Test
    void getPrice_returnSinglePrice() {
        LocalDateTime now = LocalDateTime.now();

        List<Price> prices = List.of(
                new Price(1L, 1L, 2L, now, now.plusHours(1), (byte) 1, new BigDecimal("30.00"), "EUR")
        );

        when(priceRepositoryPort.findPricesByDateAndProductAndBrand(now, 1L, 1L)).thenReturn(prices);

        Optional<Price> result = priceQueryUseCase.getPrice(now, 1L, 1L);

        assertTrue(result.isPresent());
        assertEquals(1, result.get().getPriority());
        assertEquals(new BigDecimal("30.00"), result.get().getPrice());
    }

    @DisplayName("More than one price with different priority in repository -> Return highest priority price")
    @Test
    void getPrice_returnPriceWithHighestPriority() {
        LocalDateTime now = LocalDateTime.now();

        List<Price> prices = List.of(
                new Price(1L, 1L, 1L, now, now.plusHours(1), 0, new BigDecimal("20.00"), "EUR"),
                new Price(1L, 1L, 2L, now, now.plusHours(1), 1, new BigDecimal("30.00"), "EUR")
        );

        when(priceRepositoryPort.findPricesByDateAndProductAndBrand(now, 1L, 1L)).thenReturn(prices);

        Optional<Price> result = priceQueryUseCase.getPrice(now, 1L, 1L);

        assertTrue(result.isPresent());
        assertEquals(1, result.get().getPriority());
        assertEquals(new BigDecimal("30.00"), result.get().getPrice());
    }
}
