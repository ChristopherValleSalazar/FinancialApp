package com.chrisV.BasicFinancialApp.mapperTest;

import com.chrisV.BasicFinancialApp.mapper.AccountUpgradedMapper;
import com.chrisV.BasicFinancialApp.model.Account;
import com.chrisV.BasicFinancialApp.model.AccountType;
import com.chrisV.BasicFinancialApp.model.CheckingAccountDetails;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
public class AccountMapperTest {

    private static Account account1;
    private final AccountUpgradedMapper mapper = AccountUpgradedMapper.INSTANCE;

    @BeforeAll
    public static void setUp() {
        account1 = new Account();
        account1.setAccountType(AccountType.CHECKING);
        account1.setBankName("Bank A");
        account1.setBalance(BigDecimal.valueOf(1000.00));
        account1.setNotes("Primary account for daily transactions");
        account1.setNickname("Primary Checking");

        CheckingAccountDetails checkingDetails = new CheckingAccountDetails();
        checkingDetails.setOverdraftLimit(BigDecimal.valueOf(500.00));
        checkingDetails.setMonthlyFee(BigDecimal.valueOf(12.00));
        checkingDetails.setMinimumBalance(BigDecimal.valueOf(100.00));

        account1.setCheckingAccountDetails(checkingDetails);
    }

    @Test
    public void shouldMapAccountToAccountResponseDTO() {
        var dto = mapper.toAccountResponseDTO(account1);
        assert dto != null;
        assert dto.getAccountType() == account1.getAccountType();
        assert dto.getBalance().equals(account1.getBalance());
        assert dto.getBankName().equals(account1.getBankName());
        assert dto.getNotes().equals(account1.getNotes());
        assert dto.getNickname().equals(account1.getNickname());
    }



}
