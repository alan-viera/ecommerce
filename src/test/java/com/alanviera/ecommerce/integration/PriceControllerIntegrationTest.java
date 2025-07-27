package com.alanviera.ecommerce.integration;

import com.alanviera.ecommerce.adapter.in.rest.dto.PriceResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PriceControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    static Stream<TestCase> providePriceScenarios() {
        return Stream.of(
                new TestCase(
                        LocalDateTime.of(2020, 6, 14, 10, 0),
                        35455L,
                        1L,
                        new PriceResponseDto(
                                35455L,
                                1L,
                                1L,
                                LocalDateTime.of(2020, 6, 14, 0, 0, 0),
                                LocalDateTime.of(2020, 12, 31, 23, 59, 59),
                                new BigDecimal("35.50"),
                                "EUR")
                ),
                new TestCase(
                        LocalDateTime.of(2020, 6, 14, 16, 0),
                        35455L,
                        1L,
                        new PriceResponseDto(
                                35455L,
                                1L,
                                2L,
                                LocalDateTime.of(2020, 6, 14, 15, 0, 0),
                                LocalDateTime.of(2020, 6, 14, 18, 30, 0),
                                new BigDecimal("25.45"),
                                "EUR")
                ),
                new TestCase(
                        LocalDateTime.of(2020, 6, 14, 21, 0),
                        35455L,
                        1L,
                        new PriceResponseDto(
                                35455L,
                                1L,
                                1L,
                                LocalDateTime.of(2020, 6, 14, 0, 0, 0),
                                LocalDateTime.of(2020, 12, 31, 23, 59, 59),
                                new BigDecimal("35.50"),
                                "EUR")
                ),
                new TestCase(
                        LocalDateTime.of(2020, 6, 15, 10, 0),
                        35455L,
                        1L,
                        new PriceResponseDto(
                                35455L,
                                1L,
                                3L,
                                LocalDateTime.of(2020, 6, 15, 0, 0, 0),
                                LocalDateTime.of(2020, 6, 15, 11, 0, 0),
                                new BigDecimal("30.50"),
                                "EUR")
                ),
                new TestCase(
                        LocalDateTime.of(2020, 6, 16, 21, 0),
                        35455L,
                        1L,
                        new PriceResponseDto(
                                35455L,
                                1L,
                                4L,
                                LocalDateTime.of(2020, 6, 15, 16, 0, 0),
                                LocalDateTime.of(2020, 12, 31, 23, 59, 59),
                                new BigDecimal("38.95"),
                                "EUR")
                )
        );
    }

    @ParameterizedTest
    @MethodSource("providePriceScenarios")
    void getPrice_responses(TestCase testCase) throws Exception {
        String jsonResponse = mockMvc.perform(get("/price")
                        .param("date", testCase.date.toString())
                        .param("productId", testCase.productId.toString())
                        .param("brandId", testCase.brandId.toString()))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        PriceResponseDto actualResponse = objectMapper.readValue(jsonResponse, PriceResponseDto.class);
        assertEquals(testCase.expected, actualResponse);
    }

    private static class TestCase {
        final LocalDateTime date;
        final Long productId;
        final Long brandId;
        final PriceResponseDto expected;

        TestCase(LocalDateTime date, Long productId, Long brandId, PriceResponseDto expected) {
            this.date = date;
            this.productId = productId;
            this.brandId = brandId;
            this.expected = expected;
        }
    }
}

