package com.alanviera.ecommerce.adapter.in.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PriceRequestDto {
    private LocalDateTime date;
    private Long productId;
    private Long brandId;
}
