package com.chrisV.BasicFinancialApp.dto.account;

import com.chrisV.BasicFinancialApp.model.account.AccountType;
import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class AccountResponseDTO {
    private AccountType accountType;
    private BigDecimal balance;
    private String bankName;
    private String notes;
    private String nickname;

//    private AccountDetails accountDetails;
}
