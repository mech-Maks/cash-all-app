package com.tinkoff.com.tinkoff.financialtracker.repo;

import com.tinkoff.com.tinkoff.financialtracker.domain.Category;
import com.tinkoff.com.tinkoff.financialtracker.domain.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {

    @Query("SELECT o FROM operation o " +
            "WHERE o.isDeleted = false and o.walletId = ?1")
    Optional<List<Operation>> getAllOperationsForWallet(Long walletId);

    @Query("SELECT w.payLimit FROM wallet w WHERE w.id = ?1")
    Long getWalletPayLimit(Long walletId);

    @Query("select coalesce(sum(o.amount), 0) from operation o where o.walletId = ?1 and o.operationType = 'CONSUMPTION'")
    Long getWalletConsump(Long walletId);

    @Modifying
    @Transactional
    @Query("UPDATE wallet w set w.isExceeded = true")
    void setIsExceededFlagToWallet(Long walletId);

    @Query("select c from category c where c.id = :categoryId")
    Category getCategory(Long categoryId);
}

