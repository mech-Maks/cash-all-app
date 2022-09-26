package com.tinkoff.com.tinkoff.financialtracker.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserDto {
    // @NotBlank private String login;
    // @NotBlank private String password;
    private Long id;
}
