package com.alanviera.ecommerce.adapter.in.rest;

import com.alanviera.ecommerce.adapter.in.rest.dto.PriceResponseDto;
import com.alanviera.ecommerce.domain.port.in.PriceQueryUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/price")
public class PriceController {
    private final PriceQueryUseCase priceQueryUseCase;

    public PriceController(PriceQueryUseCase priceQueryUseCase) {
        this.priceQueryUseCase = priceQueryUseCase;
    }

    @Operation(summary = "Get price for a product at a specific date for a brand")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Price found or empty response"),
            @ApiResponse(responseCode = "400", description = "Invalid request parameters"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public ResponseEntity<PriceResponseDto> getPrice(
            @Parameter(
                    description = "Date and time of application (format: yyyy-MM-dd'T'HH:mm:ss)",
                    example = "2020-06-14T21:00:00")
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime date,

            @Parameter(description = "Product ID", example = "35455")
            @RequestParam
            Long productId,

            @Parameter(description = "Brand ID", example = "1")
            @RequestParam
            Long brandId
    ) {
        return priceQueryUseCase.getPrice(date, productId, brandId)
                .map(price -> ResponseEntity.ok(PriceResponseDto.from(price)))
                .orElseGet(() -> ResponseEntity.ok().build());
    }
}
