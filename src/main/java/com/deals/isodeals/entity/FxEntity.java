package com.deals.isodeals.entity;

import jakarta.persistence.*;
import lombok.Cleanup;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Currency;

@Entity
@Table(name = "fx_iso")
@Data
@SQLDelete(sql = "UPDATE fx_iso SET deleted = true WHERE id=?")
@Where(clause = " deleted=false")
public class FxEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uniqueDealId;
    private Currency fromIsoCurrency;
    private Currency toIsoCurrency;
    private Instant dealTimestamp;
    private BigDecimal dealAmount;
    @Column(name = "deleted")
    private Boolean deleted = Boolean.FALSE;
}
