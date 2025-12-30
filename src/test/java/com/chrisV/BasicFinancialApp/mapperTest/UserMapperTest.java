package com.chrisV.BasicFinancialApp.mapperTest;

import com.chrisV.BasicFinancialApp.mapper.UserMapper;
import com.chrisV.BasicFinancialApp.model.Account;
import com.chrisV.BasicFinancialApp.model.AccountType;
import com.chrisV.BasicFinancialApp.model.CheckingAccountDetails;
import com.chrisV.BasicFinancialApp.model.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class UserMapperTest {

    private static User user;

    @Autowired
    private UserMapper mapper;

    @BeforeAll
    public static void setUp() {
        Account account1;

        // create account using the no-arg constructor and setters
        account1 = new Account();
        account1.setAccountType(AccountType.CHECKING);
        account1.setBankName("Bank A");
        account1.setBalance(BigDecimal.valueOf(1000.00));
        account1.setNickname("Primary Checking");

        CheckingAccountDetails checkingDetails = new CheckingAccountDetails();
        checkingDetails.setOverdraftLimit(BigDecimal.valueOf(500.00));
        checkingDetails.setMonthlyFee(BigDecimal.valueOf(12.00));
        checkingDetails.setMinimumBalance(BigDecimal.valueOf(100.00));

        account1.setCheckingAccountDetails(checkingDetails);

        // create user and attach accounts using helper to maintain bidirectional relationship
        user = new User();
        user.setId(1L);
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setUsername("johndoe");
        user.setPassword("password123");
        user.setEmail("john.doe@example.com");

        user.addAccount(account1);
    }

    @Test
    public void shouldMapUserToUserUpdateNameDTO() {
        var dto = mapper.userToUserUpdateNameDTO(user);
        assertThat(dto).isNotNull();
        assertThat(dto.getFirstName()).isEqualTo(user.getFirstName());
        assertThat(dto.getLastName()).isEqualTo(user.getLastName());
    }

    @Test
    public void shouldMapUserToUserUpdateUsernameDTO() {
        var dto = mapper.userToUserUpdateUsernameDTO(user);
        assertThat(dto).isNotNull();
        assertThat(dto.getUsername()).isEqualTo(user.getUsername());
    }

    @Test
    public void shouldMapUserToUserUpdateEmailDTO() {
        var dto = mapper.userToUserUpdateEmailDTO(user);
        assertThat(dto).isNotNull();
        assertThat(dto.getEmail()).isEqualTo(user.getEmail());
    }

    @Test
    public void shouldMapUserToUserResponseDTO() {
        var dto = mapper.userToUserResponseDTO(user);
        assertThat(dto).isNotNull();
        assertThat(dto.getId()).isEqualTo(user.getId());
        assertThat(dto.getFirstName()).isEqualTo(user.getFirstName());
        assertThat(dto.getLastName()).isEqualTo(user.getLastName());
        assertThat(dto.getEmail()).isEqualTo(user.getEmail());
    }

    @Test
    public void shouldMapUserResponseDTOToUser() {
        var dto = mapper.userToUserResponseDTO(user);
        User mappedUser = mapper.userResponseDTOToUser(dto);
        assertThat(mappedUser).isNotNull();
        assertThat(mappedUser.getId()).isEqualTo(dto.getId());
        assertThat(mappedUser.getFirstName()).isEqualTo(dto.getFirstName());
        assertThat(mappedUser.getLastName()).isEqualTo(dto.getLastName());
        assertThat(mappedUser.getEmail()).isEqualTo(dto.getEmail());
    }

    @Test
    public void shouldMapUserToUserRequestDTO() {
        var dto = mapper.userToUserRequestDTO(user);
        assertThat(dto).isNotNull();
        assertThat(dto.getFirstName()).isEqualTo(user.getFirstName());
        assertThat(dto.getLastName()).isEqualTo(user.getLastName());
        assertThat(dto.getEmail()).isEqualTo(user.getEmail());
        assertThat(dto.getUsername()).isEqualTo(user.getUsername());
        assertThat(dto.getPassword()).isEqualTo(user.getPassword());
    }

    @Test
    public void shouldMapUserRequestDTOToUser() {
        var dto = mapper.userToUserRequestDTO(user);
        User mappedUser = mapper.userRequestDTOToUser(dto);
        assertThat(mappedUser).isNotNull();
        assertThat(mappedUser.getFirstName()).isEqualTo(dto.getFirstName());
        assertThat(mappedUser.getLastName()).isEqualTo(dto.getLastName());
        assertThat(mappedUser.getEmail()).isEqualTo(dto.getEmail());
        assertThat(mappedUser.getUsername()).isEqualTo(dto.getUsername());
        assertThat(mappedUser.getPassword()).isEqualTo(dto.getPassword());
    }

//    @Test
//    public void shouldMapUserToUserLoginDTO() {
//        UserLoginDTO dto = mapper.userToUserLoginDTO(user);
//        assertThat(dto).isNotNull();
//        assertThat(dto.getId()).isEqualTo(user.getId());
//        assertThat(dto.getUsername()).isEqualTo(user.getUsername());
//        assertThat(dto.getPassword()).isEqualTo(user.getPassword());
//    }

//    @Test
//    public void shouldReturnNullWhenUserIsNull() {
//        UserLoginDTO dto = mapper.userToUserLoginDTO(null);
//        assertThat(dto).isNull();
//
//        var nameDto = mapper.userToUserUpdateNameDTO(null);
//        assertThat(nameDto).isNull();
//    }




}
