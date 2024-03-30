package com.deals.isodeals.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Currency;

@Entity
@Table(name = "fx_iso")
@Data
public class FxEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uniqueDealId;
    private Currency fromIsoCurrency;
    private Currency toIsoCurrency;
    private Instant dealTimestamp;
    private BigDecimal dealAmount;
}
