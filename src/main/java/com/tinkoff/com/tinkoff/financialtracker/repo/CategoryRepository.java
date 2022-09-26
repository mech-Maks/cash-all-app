package com.tinkoff.com.tinkoff.financialtracker.repo;

import com.tinkoff.com.tinkoff.financialtracker.domain.Category;
import com.tinkoff.com.tinkoff.financialtracker.domain.Colour;
import com.tinkoff.com.tinkoff.financialtracker.domain.Currency;
import com.tinkoff.com.tinkoff.financialtracker.domain.Icon;
import com.tinkoff.com.tinkoff.financialtracker.utils.OperationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("select c from category c where c.userId = :userId and c.isDeleted = false or c.isDefault = true")
    Optional<List<Category>> findAllByUserId(Long userId);

    @Modifying
    @Transactional
    @Query("UPDATE category c set c.isDeleted = true where c.id = :id and c.isDefault = false")
    void deleteCategory(Long id);

    @Query("select i from icon i")
    List<Icon> getIcons();

    @Query("select c from colour c")
    List<Colour> getColours();

    @Query("select c from currency c")
    List<Currency> getCurrencies();

    @Query("select c from category c where c.userId = :userId and c.operationType = :operationType and c.isDeleted = false or (c.isDefault = true and c.operationType = :operationType)")
    Optional<List<Category>> findAllByUserIdAndOperationType(Long userId, OperationType operationType);
}
