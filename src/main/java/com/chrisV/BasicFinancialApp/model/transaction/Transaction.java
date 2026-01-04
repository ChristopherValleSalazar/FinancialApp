package com.chrisV.BasicFinancialApp.model.transaction;

import com.chrisV.BasicFinancialApp.model.account.Account;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private TransactionType type;
    private BigDecimal amount;
    private String description;
    private String category;

    @CreationTimestamp
    private LocalDateTime timestamp;

    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private Account account;
}
