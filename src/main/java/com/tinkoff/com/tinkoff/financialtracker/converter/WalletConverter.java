package com.tinkoff.com.tinkoff.financialtracker.converter;

import com.tinkoff.com.tinkoff.financialtracker.domain.Currency;
import com.tinkoff.com.tinkoff.financialtracker.domain.Wallet;
import com.tinkoff.com.tinkoff.financialtracker.model.WalletDto;
import com.tinkoff.com.tinkoff.financialtracker.repo.WalletRepository;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Collectors;

@Component
public class WalletConverter {
    private final Map<Long, String> currencies;

    public WalletConverter(WalletRepository walletRepository) {
        this.currencies = walletRepository.getCurrencies().stream().collect(Collectors.toMap(Currency::getId, Currency::getCharCode));
    }

    public Wallet convert(WalletDto walletDto) {
        if (walletDto == null) {
            return null;
        }

        if (!currencies.containsValue(walletDto.getCurrencyName())) {
            throw new IllegalArgumentException("no such currency");
        }

        return new Wallet()
                .setId(walletDto.getId())
                .setUserId(walletDto.getUserId())
                .setName(walletDto.getName())
                .setCurrencyId(
                        currencies.entrySet().stream().filter(currency -> currency.getValue().equals(walletDto.getCurrencyName())).findFirst().get().getKey()
                )
                .setPayLimit(walletDto.getPayLimit())
                .setIsHidden(walletDto.getIsHidden())
                .setIsExceeded(walletDto.getIsExcedeed());
    }

    public WalletDto convert(Wallet wallet) {
        if (wallet == null) {
            return null;
        }

        return new WalletDto()
                .setId(wallet.getId())
                .setUserId(wallet.getUserId())
                .setName(wallet.getName())
                .setCurrencyName(currencies.get(wallet.getCurrencyId()))
                .setPayLimit(wallet.getPayLimit())
                .setIsHidden(wallet.getIsHidden())
                .setIsExcedeed(wallet.getIsExceeded());
    }
}
