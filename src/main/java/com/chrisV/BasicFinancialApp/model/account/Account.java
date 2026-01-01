package com.chrisV.BasicFinancialApp.model.account;

import com.chrisV.BasicFinancialApp.model.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@ToString(exclude = "user")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    private String bankName;
    private BigDecimal balance;

    private String notes;
    private String nickname;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private CheckingAccountDetails checkingAccountDetails;

    //setter to map bidirectional relationship for CheckingAccountDetails
    public void setCheckingAccountDetails(CheckingAccountDetails details) {
        this.checkingAccountDetails = details;
        if(details != null) {
            details.setAccount(this);
        }
    }
}
