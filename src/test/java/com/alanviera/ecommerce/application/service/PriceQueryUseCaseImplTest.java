package com.alanviera.ecommerce.application.service;

import com.alanviera.ecommerce.domain.port.out.PriceRepositoryPort;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PriceQueryUseCaseImplTest {
    @Mock
    private PriceRepositoryPort priceRepositoryPort;

    @InjectMocks
    private PriceQueryUseCaseImpl priceQueryUseCase;
}
