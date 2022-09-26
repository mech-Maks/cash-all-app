package com.tinkoff.com.tinkoff.financialtracker.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@Accessors(chain = true)
@Entity(name = "wallet")
public class Wallet {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "user_id")
    private Long userId;

    @NotBlank
    @Column(name = "wallet_name")
    private String name;

    @NotNull
    @Column(name = "currency_id")
    private Long currencyId;

    @NotNull
    @Column(name = "pay_limit")
    private Long payLimit;

    @NotNull
    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @NotNull
    @Column(name = "is_exceeded")
    private Boolean isExceeded;

    @NotNull
    @Column(name = "is_hidden")
    private Boolean isHidden;

    @NotNull
    @Column(name = "created_at")
    private LocalDate createdAt;
}