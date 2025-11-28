package com.chrisV.BasicFinancialApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CheckingAccountDetails {
    @Id
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Account account;

    private BigDecimal overdraftLimit;
    private BigDecimal monthlyFee;
    private BigDecimal minimumBalance;
//    private LocalDate lastFeeApplied;
}
