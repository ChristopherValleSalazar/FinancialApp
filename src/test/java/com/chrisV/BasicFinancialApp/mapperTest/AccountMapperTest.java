package com.chrisV.BasicFinancialApp.mapperTest;

import com.chrisV.BasicFinancialApp.dto.account.AccountRequestDTO;
import com.chrisV.BasicFinancialApp.dto.account.CheckingAccountRequest;
import com.chrisV.BasicFinancialApp.dto.account.CheckingAccountResponse;
import com.chrisV.BasicFinancialApp.mapper.AccountBaseMapper;
import com.chrisV.BasicFinancialApp.mapper.AccountMapper;
import com.chrisV.BasicFinancialApp.model.Account;
import com.chrisV.BasicFinancialApp.model.AccountType;
import com.chrisV.BasicFinancialApp.model.CheckingAccountDetails;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.math.BigDecimal;

@SpringBootTest
public class AccountMapperTest {

    private static Account account1;
    private static AccountRequestDTO accountRequestDTO1;

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private AccountBaseMapper mapper;


    @BeforeAll
    public static void setUp() {

        //set up for Entity to DTO mapping
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

        //set up for DTO to Entity mapping
        accountRequestDTO1 = new AccountRequestDTO();
        accountRequestDTO1.setAccountType(AccountType.CHECKING);
        accountRequestDTO1.setBankName("Bank B");
        accountRequestDTO1.setBalance(BigDecimal.valueOf(2000.00));
        accountRequestDTO1.setNotes("Secondary account for savings");
        accountRequestDTO1.setNickname("Savings Checking");

        CheckingAccountRequest details = new CheckingAccountRequest();
        details.setOverdraftLimit(BigDecimal.valueOf(300.00));
        details.setMonthlyFee(BigDecimal.valueOf(10.00));
        details.setMinimumBalance(BigDecimal.valueOf(200.00));

        accountRequestDTO1.setAccountDetails(details);
    }

    @Test
    public void shouldMapAccountToAccountResponseDTOWithoutDetails() {
        var dto = mapper.accountToAccountResponseDTOWithoutDetails(account1);
        assert dto != null;
        assert dto.getAccountType() == account1.getAccountType();
        assert dto.getBalance().equals(account1.getBalance());
        assert dto.getBankName().equals(account1.getBankName());
        assert dto.getNotes().equals(account1.getNotes());
        assert dto.getNickname().equals(account1.getNickname());
    }

    @Test
    public void shouldMapAccountToAccountResponseDTOWithDetails() {

        //mapping generic fields and details using AccountMapper
        var dto2 = accountMapper.fromEntityToResponseDTO(account1);
        assertThat(dto2).isNotNull();
        assert dto2.getAccountType() == account1.getAccountType();
        assert dto2.getBalance().equals(account1.getBalance());
        assert dto2.getBankName().equals(account1.getBankName());
        assert dto2.getNotes().equals(account1.getNotes());
        assert dto2.getNickname().equals(account1.getNickname());
        assert dto2.getAccountDetails() != null;
        assert dto2.getAccountDetails().getClass().getSimpleName().equals("CheckingAccountResponse");

        CheckingAccountResponse details = (CheckingAccountResponse) dto2.getAccountDetails();
        assert details != null;
        assertThat(details.getClass()).isEqualTo(CheckingAccountResponse.class);
        assert details.getOverdraftLimit().equals(account1.getCheckingAccountDetails().getOverdraftLimit());
        assert details.getMonthlyFee().equals(account1.getCheckingAccountDetails().getMonthlyFee());
        assert details.getMinimumBalance().equals(account1.getCheckingAccountDetails().getMinimumBalance());
    }

    @Test
    public void shouldMapAccountRequestDtoToEntity() {
        var dto = accountMapper.fromRequestDtoToEntity(accountRequestDTO1);
        assertThat(dto).isNotNull();
        assert dto.getAccountType() == accountRequestDTO1.getAccountType();
        assert dto.getBalance().equals(accountRequestDTO1.getBalance());
        assert dto.getBankName().equals(accountRequestDTO1.getBankName());
        assert dto.getNotes().equals(accountRequestDTO1.getNotes());
        assert dto.getNickname().equals(accountRequestDTO1.getNickname());
        assert dto.getCheckingAccountDetails() != null;
        CheckingAccountDetails details = dto.getCheckingAccountDetails();
        CheckingAccountRequest requestDetails = (CheckingAccountRequest) accountRequestDTO1.getAccountDetails();
        assert details.getOverdraftLimit().equals(requestDetails.getOverdraftLimit());
        assert details.getMonthlyFee().equals(requestDetails.getMonthlyFee());
        assert details.getMinimumBalance().equals(requestDetails.getMinimumBalance());
    }
}
