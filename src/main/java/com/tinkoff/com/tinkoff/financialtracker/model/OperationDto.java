package com.tinkoff.com.tinkoff.financialtracker.model;

import com.tinkoff.com.tinkoff.financialtracker.utils.OperationType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Accessors(chain = true)
public class OperationDto {
    @Schema(description = "Необязательный атрибут при создании операции")
    private Long id;
    @NotNull private Long walletId;
    @NotNull private Long categoryId;
    @NotNull private Long amount;
    @NotBlank private String currencyName;
    @NotNull private OperationType operationType;
    @Schema(description = "Необязательный атрибут при создании операции")
    private CategoryDto category;
    @Schema(description = "Необязательный атрибут при создании операции")
    private Long createdAt;
}
