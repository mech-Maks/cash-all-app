package com.tinkoff.com.tinkoff.financialtracker.service;

import com.tinkoff.com.tinkoff.financialtracker.converter.WalletConverter;
import com.tinkoff.com.tinkoff.financialtracker.domain.Wallet;
import com.tinkoff.com.tinkoff.financialtracker.model.WalletDto;
import com.tinkoff.com.tinkoff.financialtracker.repo.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WalletService {
    private final WalletRepository walletRepository;
    private final WalletConverter walletConverter;

    public List<WalletDto> findAllWalletsByUserId(Long userId) {
        return walletRepository.findAllByUserId(userId).orElseThrow(()-> new IllegalArgumentException("No such id"))
                .stream().map(walletConverter::convert).map(this::setWalletEconomy).collect(Collectors.toList());
    }

    public WalletDto findWallet(Long walletId) {
        return setWalletEconomy(walletConverter.convert((walletRepository.findWalletById(walletId))
                .orElseThrow(()-> new IllegalArgumentException("No such walletId"))));
    }

    public WalletDto updateWallet(WalletDto walletDto) {
        walletRepository.findById(walletDto.getId())
                .map(entity -> updateWallet(entity, walletConverter.convert(walletDto)))
                .map(walletRepository::save).orElseThrow(()-> new IllegalArgumentException("No such walletId"));
        return walletConverter.convert(walletRepository.findById(walletDto.getId()).orElse(null));
    }

    public ResponseEntity<Object> deleteWallet(Long id) {
        walletRepository.findById(id)
                .map(entity -> entity.setIsDeleted(true))
                .map(walletRepository::save)
                .orElseThrow(()-> new IllegalArgumentException("No such walletId"));
        return ResponseEntity.status(HttpStatus.OK)
                .body(Map.of("success", true, "message", "The operation was successfull!"));
    }

    public WalletDto createWallet(WalletDto walletDto) {
        return walletConverter.convert(walletRepository.save(createDefaultWallet(walletDto)))
                .setBalance(0L).setFullConsump(0L).setFullIncome(0L);
    }

    public ResponseEntity<Object> hideWallet(Long id) {
        walletRepository.findById(id)
                .map(entity -> entity.setIsHidden(true))
                .map(walletRepository::save)
                .orElseThrow(()-> new IllegalArgumentException("No such walletId"));
        return ResponseEntity.status(HttpStatus.OK)
                .body(Map.of("success", true, "message", "The operation was successfull!"));
    }

    public ResponseEntity<Object> showWallet(Long id) {
        walletRepository.findById(id)
                .map(entity -> entity.setIsHidden(false))
                .map(walletRepository::save)
                .orElseThrow(()-> new IllegalArgumentException("No such walletId"));
        return ResponseEntity.status(HttpStatus.OK)
                .body(Map.of("success", true, "message", "The operation was successfull!"));
    }

    private Wallet createDefaultWallet(WalletDto walletDto) {
        return walletConverter.convert(walletDto)
                .setIsDeleted(false)
                .setIsExceeded(false)
                .setIsHidden(false)
                .setCreatedAt(LocalDate.now());
    }

    private Wallet updateWallet(Wallet oldWallet, Wallet newWallet) {
        if (walletRepository.getFullConsump(newWallet.getId()) < newWallet.getPayLimit()) {
            oldWallet.setIsExceeded(false);
        }
        return oldWallet.setName(newWallet.getName())
                .setCurrencyId(newWallet.getCurrencyId())
                .setPayLimit(newWallet.getPayLimit());
    }

    private WalletDto setWalletEconomy(WalletDto walletDto) {
        walletDto.setFullConsump(walletRepository.getFullConsump(walletDto.getId()))
                .setFullIncome(walletRepository.getFullIncome(walletDto.getId()));
        walletDto.setBalance(walletDto.getFullIncome() - walletDto.getFullConsump());
        return walletDto;
    }
}