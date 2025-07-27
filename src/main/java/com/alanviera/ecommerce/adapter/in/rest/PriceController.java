package com.alanviera.ecommerce.adapter.in.rest;

import com.alanviera.ecommerce.adapter.in.rest.dto.PriceResponseDto;
import com.alanviera.ecommerce.domain.port.in.PriceQueryUseCase;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/prices")
public class PriceController {
    private final PriceQueryUseCase priceQueryUseCase;

    public PriceController(PriceQueryUseCase priceQueryUseCase) {
        this.priceQueryUseCase = priceQueryUseCase;
    }

    @GetMapping
    public ResponseEntity<PriceResponseDto> getPrice(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date,
            @RequestParam Long productId,
            @RequestParam Long brandId
    ) {
        return priceQueryUseCase.getPrice(date, productId, brandId)
                .map(price -> ResponseEntity.ok(PriceResponseDto.from(price)))
                .orElseGet(() -> ResponseEntity.ok().build());
    }
}
