package com.tinkoff.com.tinkoff.financialtracker.controllers;

import com.tinkoff.com.tinkoff.financialtracker.domain.Currency;
import com.tinkoff.com.tinkoff.financialtracker.service.CurrencyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/currency")
@RestController
@Tag(description = "Api to manage currency",
        name = "Currency Resource")
@RequiredArgsConstructor
public class CurrencyController {

    private final CurrencyService currencyService;

    @Operation(summary = "Get currencies",
            description = "Provides all currencies")
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Currency> getCurrencies(){
        return currencyService.findAllCurrencies();
    }

    @Operation(summary = "Create currency",
            description = "Creates new currency")
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addCurrency(@Valid @RequestBody Currency currency) {
        currencyService.addCurrency(currency);
    }
}