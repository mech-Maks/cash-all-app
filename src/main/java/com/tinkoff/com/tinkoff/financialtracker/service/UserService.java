package com.tinkoff.com.tinkoff.financialtracker.service;

import com.tinkoff.com.tinkoff.financialtracker.model.UserDto;
import com.tinkoff.com.tinkoff.financialtracker.repo.UserRepository;
import com.tinkoff.com.tinkoff.financialtracker.utils.CurrencyRelationship;
import com.tinkoff.com.tinkoff.financialtracker.utils.Economy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public String auth(UserDto user) {
//        System.out.printf("login: %s, pass: %s\n", user.getLogin(), user.getPassword());
//
//        if ("login".equals(user.getLogin()) && "pass".equals(user.getPassword())) {
//            return "OK";
//        } else {
//            throw new AuthException("bad authentication");
//        }
        return "";
    }

    public Economy getUserEconomy(Long id, CurrencyRelationship curr) {
        Double income = userRepository.getCurrencyIncomeBucket(id).stream()
                .map(curSum -> CurrencyRelationship.of(curSum.getCurrencyId()).getRelationship() * curSum.getAmount() / Double.valueOf(curr.getRelationship()))
                .reduce(Double::sum)
                .orElse(0.0);

        Double consumption = userRepository.getCurrencyConsumptionBucket(id).stream()
                .map(curSum -> CurrencyRelationship.of(curSum.getCurrencyId()).getRelationship() * curSum.getAmount() / Double.valueOf(curr.getRelationship()))
                .reduce(Double::sum)
                .orElse(0.0);

        return new Economy()
                .setIncome(String.format("%.2f", income))
                .setConsumption(String.format("%.2f", consumption))
                .setBalance(String.format("%.2f", income - consumption));
    }
}
