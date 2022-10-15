package com.tinkoff.com.tinkoff.financialtracker.model;

import com.tinkoff.com.tinkoff.financialtracker.utils.OperationType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {

    @Schema(description = "Необязательный атрибут при создании категории")
    private Long id;

    @NotBlank
    private String name;

    @NotNull
    private Long userId;

    @NotNull
    private String colourName;

    @NotNull
    private String iconName;

    @NotNull
    private OperationType operationType;

    @Schema(description = "Необязательный атрибут при создании категории")
    private Boolean isDefault;
}