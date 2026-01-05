package com.chrisV.BasicFinancialApp.model.account;

import com.chrisV.BasicFinancialApp.model.transaction.TransactionCategory;
import com.chrisV.BasicFinancialApp.model.transaction.TransactionType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue
    private Long id;

    @Setter
    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @Setter
    @Column(nullable = false)
    private BigDecimal amount;

    @Setter
    private String description;

    @Setter
    private TransactionCategory category;

    @CreationTimestamp
    private LocalDateTime timestamp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = true)
    private Account account;

    void setAccount(Account account) {
        this.account = account;
    }


}
