package com.chrisV.BasicFinancialApp.model.transaction;

import com.chrisV.BasicFinancialApp.model.account.Account;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Transaction {

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private TransactionType type;
    private BigDecimal amount;
    private String description;
    private LocalDateTime timestamp;
    private String category;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private Account account;
}
