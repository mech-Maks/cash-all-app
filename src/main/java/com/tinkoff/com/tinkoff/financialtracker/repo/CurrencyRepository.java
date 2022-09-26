package com.tinkoff.com.tinkoff.financialtracker.repo;

import com.tinkoff.com.tinkoff.financialtracker.domain.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {
}
