package com.chrisV.BasicFinancialApp.dto.account;

import com.chrisV.BasicFinancialApp.model.AccountType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountUpdateRequestDTO {
    private AccountType accountType;
    private String bankName;
    private String notes;
    private String nickname;

//    // Polymorphic deserialization based on accountType
//    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
//            include = JsonTypeInfo.As.EXTERNAL_PROPERTY,
//            property = "accountType",
//            visible = false)
//    @JsonSubTypes({
//            @JsonSubTypes.Type(value = CheckingAccountRequest.class, name = "CHECKING")
//    })
//    private AccountDetails accountDetails;
}
