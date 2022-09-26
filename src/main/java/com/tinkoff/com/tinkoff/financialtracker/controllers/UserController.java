package com.tinkoff.com.tinkoff.financialtracker.controllers;

import com.tinkoff.com.tinkoff.financialtracker.model.UserDto;
import com.tinkoff.com.tinkoff.financialtracker.service.UserService;
import com.tinkoff.com.tinkoff.financialtracker.utils.CurrencyRelationship;
import com.tinkoff.com.tinkoff.financialtracker.utils.Economy;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Tag(description = "Api to manage user",
        name = "User Resource")
public class UserController {
    private final UserService userService;

    @Operation(summary = "Get economy for user (balance, fullIncome, fullConsumption)",
            description = "Provides user economy")
    @GetMapping("/economy/{userId}/{curr}")
    public Economy getUserEconomy(@PathVariable Long userId, @PathVariable CurrencyRelationship curr) {
        return userService.getUserEconomy(userId, curr);
    }

    @PostMapping("/auth")
    public String auth(@Valid @RequestBody UserDto user) {
        return userService.auth(user);
    }
}
