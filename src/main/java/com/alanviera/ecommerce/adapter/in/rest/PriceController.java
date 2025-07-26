package com.alanviera.ecommerce.adapter.in.rest;

import com.alanviera.ecommerce.adapter.in.rest.dto.PriceRequestDto;
import com.alanviera.ecommerce.adapter.in.rest.dto.PriceResponseDto;
import com.alanviera.ecommerce.domain.port.in.PriceQueryUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/prices")
public class PriceController {
    private final PriceQueryUseCase priceQueryUseCase;

    public PriceController(PriceQueryUseCase priceQueryUseCase) {
        this.priceQueryUseCase = priceQueryUseCase;
    }

    @PostMapping
    public ResponseEntity<PriceResponseDto> getPrice(@RequestBody PriceRequestDto requestDto) {
        return null;
    }
}
