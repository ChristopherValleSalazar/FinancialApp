package com.chrisV.BasicFinancialApp.dto.account;

import java.math.BigDecimal;

public class CreateSavingsDTO {
    private Long userId;
    private String name;
    private BigDecimal apy;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getApy() {
        return apy;
    }

    public void setApy(BigDecimal apy) {
        this.apy = apy;
    }
}
