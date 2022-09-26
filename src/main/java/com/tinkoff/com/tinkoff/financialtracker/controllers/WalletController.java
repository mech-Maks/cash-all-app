package com.tinkoff.com.tinkoff.financialtracker.controllers;

import com.tinkoff.com.tinkoff.financialtracker.model.WalletDto;
import com.tinkoff.com.tinkoff.financialtracker.service.WalletService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;


@RequestMapping("/wallet")
@RestController
@RequiredArgsConstructor
@Tag(description = "Api to manage wallet",
        name = "Wallet Resource")
public class WalletController {

    private final WalletService walletService;

    @Operation(summary = "Get wallets for user",
            description = "Provides all wallets for user")
    @GetMapping(value = "/all/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<WalletDto> getWalletsByUserId(@PathVariable Long userId){
        return walletService.findAllWalletsByUserId(userId);
    }

    @Operation(summary = "Create wallet",
            description = "Creates new wallet")
    @PostMapping(value = "/wallet", consumes = MediaType.APPLICATION_JSON_VALUE)
    public WalletDto createWallet(@Valid @RequestBody WalletDto walletDto){
        return walletService.createWallet(walletDto);
    }

    @Operation(summary = "Get wallet for user",
            description = "Provides wallet for user")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public WalletDto getWallet(@PathVariable(name = "id") Long id){
        return walletService.findWallet(id);
    }

    @Operation(summary = "Update wallet",
            description = "Provides new updated wallet")
    @PutMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public WalletDto updateWallet(@Valid @RequestBody WalletDto walletDto){
        return walletService.updateWallet(walletDto);
    }

    @Operation(summary = "Delete wallet for user",
            description = "Delete the desired wallet for user.")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteWallet(@PathVariable(name = "id") Long id){
        return walletService.deleteWallet(id);
    }

    @Operation(summary = "Hide wallet",
            description = "Hides wallet")
    @PutMapping(value = "/hide/{id}")
    public ResponseEntity<Object> hideWallet(@PathVariable(name = "id") Long id) {
        return walletService.hideWallet(id);
    }

    @Operation(summary = "Show wallet",
            description = "Shows wallet")
    @PutMapping(value = "/show/{id}")
    public ResponseEntity<Object> showWallet(@PathVariable(name = "id") Long id) {
        return walletService.showWallet(id);
    }
}