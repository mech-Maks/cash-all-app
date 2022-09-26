package com.tinkoff.com.tinkoff.financialtracker.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Accessors(chain = true)
public class WalletDto {

    @Schema(description = "Необязательный атрибут при создании кошелька")
    private Long id;

    @NotNull
    private Long userId;

    @NotBlank
    private String name;

    @NotBlank
    private String currencyName;

    @NotNull
    private Long payLimit;

    @Schema(description = "Необязательный атрибут при создании кошелька")
    private Long balance;

    @Schema(description = "Необязательный атрибут при создании кошелька")
    private Long fullIncome;

    @Schema(description = "Необязательный атрибут при создании кошелька")
    private Long fullConsump;

    @Schema(description = "Необязательный атрибут при создании кошелька")
    private Boolean isHidden;

    @Schema(description = "Необязательный атрибут при создании кошелька")
    private Boolean isExcedeed;
}