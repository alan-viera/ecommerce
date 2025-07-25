package com.alanviera.ecommerce.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "prices")
@Data
public class PriceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "brand_id")
    private Long brandId;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "price_list_id")
    private Long priceListId;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "priority")
    private int priority;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "currency")
    private String currency;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

    @Column(name = "last_update_by")
    private String lastUpdateBy;
}
