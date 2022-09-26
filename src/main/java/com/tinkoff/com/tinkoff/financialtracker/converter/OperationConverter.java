package com.tinkoff.com.tinkoff.financialtracker.converter;

import com.tinkoff.com.tinkoff.financialtracker.domain.Currency;
import com.tinkoff.com.tinkoff.financialtracker.domain.Operation;
import com.tinkoff.com.tinkoff.financialtracker.model.OperationDto;
import com.tinkoff.com.tinkoff.financialtracker.repo.CategoryRepository;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class OperationConverter {
    private final Map<Long, String> currencies;

    public OperationConverter(CategoryRepository categoryRepository) {
        currencies = categoryRepository.getCurrencies().stream().collect(Collectors.toMap(Currency::getId, Currency::getCharCode));
    }

    public Operation convert (OperationDto operationDto) {
        if (operationDto == null) {
            return null;
        }

        if (!currencies.containsValue(operationDto.getCurrencyName())) {
            throw new IllegalArgumentException("no such currency");
        }

        return new Operation()
                .setWalletId(operationDto.getWalletId())
                .setCategoryId(operationDto.getCategoryId())
                .setId(operationDto.getId())
                .setAmount(operationDto.getAmount())
                .setOperationType(operationDto.getOperationType())
                .setCurrencyId(
                        currencies.entrySet().stream().filter(currency -> currency.getValue().equals(operationDto.getCurrencyName())).findFirst().get().getKey()
                )
                .setCreatedAt(
                        Optional.ofNullable(operationDto.getCreatedAt())
                                .map(Instant::ofEpochSecond)
                                .orElseGet(Instant::now)
                );
    }

    public OperationDto convert (Operation operation) {
        if (operation == null) {
            return null;
        }

        return new OperationDto()
                .setWalletId(operation.getWalletId())
                .setId(operation.getId())
                .setCategoryId(operation.getCategoryId())
                .setAmount(operation.getAmount())
                .setOperationType(operation.getOperationType())
                .setCurrencyName(currencies.get(operation.getCurrencyId()))
                .setCreatedAt(operation.getCreatedAt().getEpochSecond());
    }
}
