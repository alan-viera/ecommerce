package com.alanviera.ecommerce.adapter.in.rest;

import com.alanviera.ecommerce.adapter.in.rest.dto.PriceRequestDto;
import com.alanviera.ecommerce.adapter.in.rest.dto.PriceResponseDto;
import com.alanviera.ecommerce.domain.model.Price;
import com.alanviera.ecommerce.domain.port.in.PriceQueryUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PriceController.class)
public class PriceControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private PriceQueryUseCase priceQueryUseCase;

    @Test
    @DisplayName("Should return empty response")
    void getPrice_noResult() throws Exception {
        PriceRequestDto requestDto = new PriceRequestDto(
                LocalDateTime.of(2020, 6, 14, 15, 0),
                35455L,
                1L
        );

        when(priceQueryUseCase.getPrice(requestDto.getDate(), requestDto.getProductId(), requestDto.getBrandId()))
                .thenReturn(Optional.empty());

        MvcResult result = mockMvc.perform(post("/prices")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();

        assertThat(responseBody).isBlank();
    }

    @Test
    @DisplayName("Should return price when found")
    void getPrice_found() throws Exception {
        Long productId = 35455L;
        Long brandId = 1L;
        PriceRequestDto requestDto = new PriceRequestDto(
                LocalDateTime.of(2020, 6, 14, 15, 0),
                productId,
                brandId
        );

        Long priceListId = 2L;
        LocalDateTime startDate = LocalDateTime.of(2020, 6, 14, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(2020, 12, 31, 23, 59);
        BigDecimal priceValue = new BigDecimal("35.50");
        String currency = "EUR";
        Price price = new Price(productId, brandId, priceListId, startDate, endDate, (byte) 1, priceValue, currency);

        when(priceQueryUseCase.getPrice(requestDto.getDate(), requestDto.getProductId(), requestDto.getBrandId()))
                .thenReturn(Optional.of(price));

        MvcResult result = mockMvc.perform(post("/prices")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andReturn();

        String responseJson = result.getResponse().getContentAsString();
        PriceResponseDto responseDto = objectMapper.readValue(responseJson, PriceResponseDto.class);

        assertThat(responseDto.getProductId()).isEqualTo(productId);
        assertThat(responseDto.getBrandId()).isEqualTo(brandId);
        assertThat(responseDto.getPriceListId()).isEqualTo(priceListId);
        assertThat(responseDto.getStartDate()).isEqualTo(startDate);
        assertThat(responseDto.getEndDate()).isEqualTo(endDate);
        assertThat(responseDto.getPrice()).isEqualTo(priceValue);
        assertThat(responseDto.getCurrency()).isEqualTo(currency);
    }
}
