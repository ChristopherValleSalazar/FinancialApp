package com.chrisV.BasicFinancialApp.model.user;

import com.chrisV.BasicFinancialApp.model.account.Account;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString(exclude = {"password", "accounts"})
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

//    @Column(nullable = false)
//    private String salt;

    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Account> accounts = new ArrayList<>();

    // helper method (optional but recommended)
    public void addAccount(Account account) {
        accounts.add(account);
        account.setUser(this);
    }
}
