package com.deals.isodeals.service.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
@Data
public class FxDTO implements Serializable {
    private String uniqueDealId;
    private String fromIsoCurrency;
    private String toIsoCurrency;
    private Instant dealTimestamp;
    private BigDecimal dealAmount;
}
