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

@Getter
@Setter
@Accessors(chain = true)
@Entity(name = "currency")
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "currency_name")
    private String name;

    @NotBlank
    @Column(name = "char_code")
    private String charCode;

    @NotBlank
    @Column(name = "unicode")
    private String unicode;
}