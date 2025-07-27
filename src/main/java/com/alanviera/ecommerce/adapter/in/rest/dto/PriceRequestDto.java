package com.alanviera.ecommerce.adapter.in.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PriceRequestDto {
    @NotNull
    @Schema(type = "string", pattern = "yyyy-MM-dd'T'HH:mm:ss", example = "2020-06-14T21:00:00")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime date;

    @NotNull
    @Min(1)
    private Long productId;

    @NotNull
    @Min(1)
    private Long brandId;
}
