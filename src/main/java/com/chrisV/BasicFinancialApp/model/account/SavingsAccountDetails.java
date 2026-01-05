package com.chrisV.BasicFinancialApp.model.account;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class SavingsAccountDetails {

    @Id
    private Long id;

    @OneToOne
    @MapsId
    private Account account;

    private BigDecimal apy; // Annual Percentage Yield
    private final int compoundingFrequency = 1; // Monthly compounding
    private LocalDate lastInterestApplied;

    public SavingsAccountDetails() {
    }

    public SavingsAccountDetails(Long id, Account account, BigDecimal apy, LocalDate lastInterestApplied) {
        this.id = id;
        this.account = account;
        this.apy = apy;
        this.lastInterestApplied = lastInterestApplied;
    }

    // Getters and Setters
    public Account getAccount() {return account;}

    public void setAccount(Account account) {this.account = account;}

    public BigDecimal getApy() {return apy;}

    public void setApy(BigDecimal apy) {this.apy = apy;}

    public int getCompoundingFrequency() {return compoundingFrequency;}

    public LocalDate getLastInterestApplied() {return lastInterestApplied;}

    public void setLastInterestApplied(LocalDate lastInterestApplied) {this.lastInterestApplied = lastInterestApplied;}
}
