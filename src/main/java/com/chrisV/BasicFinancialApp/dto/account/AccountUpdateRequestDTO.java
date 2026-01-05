package com.chrisV.BasicFinancialApp.dto.account;

import com.chrisV.BasicFinancialApp.model.account.AccountType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccountUpdateRequestDTO {
    private AccountType accountType;
    private String bankName;
    private String notes;
    private String nickname;
}
