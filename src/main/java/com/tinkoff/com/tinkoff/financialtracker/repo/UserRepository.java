package com.tinkoff.com.tinkoff.financialtracker.repo;

import com.tinkoff.com.tinkoff.financialtracker.domain.User;
import com.tinkoff.com.tinkoff.financialtracker.utils.UserCurrencyBucket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
        @Query("select w.currencyId as currencyId, coalesce(sum(o.amount), 0) as amount " +
                "from wallet w join operation o on w.id = o.walletId " +
                "where w.userId = :id and o.operationType = 'INCOME' and o.isDeleted = false " +
                "group by w.currencyId")
        List<UserCurrencyBucket> getCurrencyIncomeBucket(Long id);

        @Query("select w.currencyId as currencyId, coalesce(sum(o.amount), 0) as amount " +
                "from wallet w join operation o on w.id = o.walletId " +
                "where w.userId = :id and o.operationType = 'CONSUMPTION' and o.isDeleted = false " +
                "group by w.currencyId")
        List<UserCurrencyBucket> getCurrencyConsumptionBucket(Long id);
}


