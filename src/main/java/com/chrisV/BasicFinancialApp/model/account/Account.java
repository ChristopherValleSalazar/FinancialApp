package com.chrisV.BasicFinancialApp.model.account;

import com.chrisV.BasicFinancialApp.model.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@ToString(exclude = {"user", "transactions", "checkingAccountDetails"})
@NoArgsConstructor
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @Setter
    private String bankName;

    private BigDecimal balance = BigDecimal.ZERO;

    @Setter
    private String notes;

    @Setter
    private String nickname;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private CheckingAccountDetails checkingAccountDetails;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaction> transactions = new ArrayList<>();

    public void applyIncome(BigDecimal amount) {
        validateAmount(amount);
        this.balance = this.balance.add(amount);
    }

    public void applyExpense(BigDecimal amount) {
        validateAmount(amount);
        this.balance = this.balance.subtract(amount);
    }

    public void addTransaction(Transaction transaction) {
        if (transaction == null) {
            throw new IllegalArgumentException("Transaction cannot be null");
        }
        transactions.add(transaction);
        transaction.setAccount(this);
    }

    public void setUser(User user) {
        this.user = user;
    }

    private void validateAmount(BigDecimal amount) {
        if (amount == null || amount.signum() <= 0) {
            throw new IllegalArgumentException("Amount must be a positive value");
        }
    }

    public void setCheckingAccountDetails(CheckingAccountDetails details) {
        this.checkingAccountDetails = details;
        if(details != null) {
            details.setAccount(this);
        }
    }


}

