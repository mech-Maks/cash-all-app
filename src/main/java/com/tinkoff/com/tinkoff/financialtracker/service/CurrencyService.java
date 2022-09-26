package com.tinkoff.com.tinkoff.financialtracker.service;

import com.tinkoff.com.tinkoff.financialtracker.domain.Currency;
import com.tinkoff.com.tinkoff.financialtracker.repo.CurrencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrencyService {
    private final CurrencyRepository currencyRepository;

    public List<Currency> findAllCurrencies() {
        return currencyRepository.findAll();
    }

    public void addCurrency(Currency currency) {
        currencyRepository.save(currency);
    }
}