package com.tinkoff.com.tinkoff.financialtracker.repo;

import com.tinkoff.com.tinkoff.financialtracker.domain.Currency;
import com.tinkoff.com.tinkoff.financialtracker.domain.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {

    @Query("select w from wallet w where w.userId = :userId and w.isDeleted = false")
    Optional<List<Wallet>> findAllByUserId(Long userId);

    @Query("select w from wallet w where w.id = :id and w.isDeleted = false")
    Optional<Wallet> findWalletById(Long id);

    @Query("select coalesce(sum(o.amount), 0) from operation o where o.walletId = ?1 and o.operationType = 'INCOME' and o.isDeleted = false ")
    Long getFullIncome(Long walletId);

    @Query("select coalesce(sum(o.amount), 0) from operation o where o.walletId = ?1 and o.operationType = 'CONSUMPTION' and o.isDeleted = false ")
    Long getFullConsump(Long walletId);

    @Query("select c from currency c")
    List<Currency> getCurrencies();
}
