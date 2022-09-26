package com.tinkoff.com.tinkoff.financialtracker.domain;

import com.tinkoff.com.tinkoff.financialtracker.utils.OperationType;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Getter
@Setter
@Accessors(chain = true)
@Entity(name = "operation")
public class Operation {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column(name = "wallet_id")
    private Long walletId;
    @NotNull
    @Column(name = "category_id")
    private Long categoryId;
    @NotNull
    @Column(name = "currency_id")
    private Long currencyId;
    @Column(name = "amount")
    @NotNull
    private Long amount;
    @Enumerated(EnumType.STRING)
    private OperationType operationType;
    @NotNull
    @Column(name = "created_at")
    private Instant createdAt;
    @NotNull
    private boolean isDeleted;
}