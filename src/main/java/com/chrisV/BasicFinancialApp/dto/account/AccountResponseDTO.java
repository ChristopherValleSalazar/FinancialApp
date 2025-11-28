package com.chrisV.BasicFinancialApp.dto.account;

import com.chrisV.BasicFinancialApp.model.AccountType;
import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponseDTO {
    private AccountType accountType;
    private BigDecimal balance;
    private String bankName;
    private String notes;
    private String nickname;

    private AccountDetails accountDetails;
}
