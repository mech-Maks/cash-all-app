package com.tinkoff.com.tinkoff.financialtracker.controllers;

import com.tinkoff.com.tinkoff.financialtracker.model.OperationDto;
import com.tinkoff.com.tinkoff.financialtracker.service.OperationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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

@RestController
@RequestMapping("/wallet")
@RequiredArgsConstructor
@Tag(description = "Api to manage operation",
        name = "Operation Resource")
@Validated
public class OperationController {
    private final OperationService operationService;

    @Operation(summary = "Get operations for wallet",
            description = "Provides all operations for wallet")
    @GetMapping(value = "/operation/all/{walletId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OperationDto> getAllOperationsForWallet(@PathVariable Long walletId) {
        return operationService.getAllOperationsForWallet(walletId);
    }

    @Operation(summary = "Create operation",
            description = "Creates new operation")
    @PostMapping(value = "/operation", consumes = MediaType.APPLICATION_JSON_VALUE)
    public OperationDto addOperationForWallet(@Valid @RequestBody OperationDto operation) {
        return operationService.addOperationForWallet(operation);
    }

    @Operation(summary = "Get operation for wallet",
            description = "Provides operation for wallet")
    @GetMapping(value = "/operation/{operationId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public OperationDto getOperation(@PathVariable Long operationId) {
        return operationService.getOperation(operationId);
    }

    @Operation(summary = "Update operation",
            description = "Provides new updated operation")
    @PutMapping(value = "/operation", consumes = MediaType.APPLICATION_JSON_VALUE)
    public OperationDto saveOperationChanges(@Valid @RequestBody OperationDto operation) {
        return operationService.saveOperationChanges(operation);
    }

    @Operation(summary = "Delete operation for wallet",
            description = "Delete the desired operation for wallet.")
    @DeleteMapping("/operation/{operationId}")
    public ResponseEntity<Object> deleteOperation(@PathVariable Long operationId) {
        return operationService.deleteOperation(operationId);
    }
}
