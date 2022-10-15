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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@Accessors(chain = true)
@Entity(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "category_name")
    private String name;

    @NotNull
    @Column(name = "user_id")
    private Long userId;

    @NotNull
    @Column(name = "colour_id")
    private Long colourId;

    @NotNull
    @Column(name = "icon_id")
    private Long iconId;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "operation_type")
    private OperationType operationType;

    @NotNull
    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @NotNull
    @Column(name = "is_default")
    private Boolean isDefault;

    @NotNull
    @Column(name = "created_at")
    private LocalDate createdAt;
}