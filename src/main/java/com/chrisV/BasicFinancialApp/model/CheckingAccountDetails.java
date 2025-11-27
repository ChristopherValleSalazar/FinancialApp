package com.chrisV.BasicFinancialApp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;

import java.math.BigDecimal;

@Entity
public class CheckingAccountDetails {
    @Id
    private Long id;

    @OneToOne
    @MapsId
    private Account account;

    private BigDecimal overdraftLimit;
    private BigDecimal monthlyFee;
    private BigDecimal minimumBalance;
//    private LocalDate lastFeeApplied;

    public CheckingAccountDetails() {}

    public CheckingAccountDetails(Long id, Account account, BigDecimal overdraftLimit, BigDecimal monthlyFee, BigDecimal minimumBalance) {
        this.id = id;
        this.account = account;
        this.overdraftLimit = overdraftLimit;
        this.monthlyFee = monthlyFee;
        this.minimumBalance = minimumBalance;
    }

    public Long id() {return id;}

    public void setId(Long id) {this.id = id;}

    public Account account() {return account;}

    public void setAccount(Account account) {this.account = account;}

    public BigDecimal overdraftLimit() {return overdraftLimit;}

    public void setOverdraftLimit(BigDecimal overdraftLimit) {this.overdraftLimit = overdraftLimit;}

    public BigDecimal monthlyFee() {return monthlyFee;}

    public void setMonthlyFee(BigDecimal monthlyFee) {this.monthlyFee = monthlyFee;}

    public BigDecimal minimumBalance() {return minimumBalance;}

    public void setMinimumBalance(BigDecimal minimumBalance) {this.minimumBalance = minimumBalance;}
}
