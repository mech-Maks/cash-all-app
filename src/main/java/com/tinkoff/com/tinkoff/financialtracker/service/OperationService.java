package com.tinkoff.com.tinkoff.financialtracker.service;

import com.tinkoff.com.tinkoff.financialtracker.converter.CategoryConverter;
import com.tinkoff.com.tinkoff.financialtracker.converter.OperationConverter;
import com.tinkoff.com.tinkoff.financialtracker.domain.Operation;
import com.tinkoff.com.tinkoff.financialtracker.model.OperationDto;
import com.tinkoff.com.tinkoff.financialtracker.repo.OperationRepository;
import com.tinkoff.com.tinkoff.financialtracker.utils.OperationType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OperationService {

    private final OperationRepository operationRepository;
    private final OperationConverter operationConverter;
    private final CategoryConverter categoryConverter;

    public List<OperationDto> getAllOperationsForWallet(Long walletId) {
        return operationRepository.getAllOperationsForWallet(walletId)
                .orElseThrow(()-> new IllegalArgumentException("No such WalletId"))
                .stream().map(operationConverter::convert)
                .map(operationDto -> operationDto.setCategory(categoryConverter.convert(operationRepository.getCategory(operationDto.getCategoryId()))))
                .collect(Collectors.toList());
    }

    @Transactional
    public OperationDto addOperationForWallet(OperationDto operationDto) {
        checkLimit(operationDto);
        return operationConverter.convert(operationRepository.save(operationConverter.convert(operationDto).setDeleted(false)));
    }

    public OperationDto getOperation(Long id) {
        return operationRepository.findById(id).map(operationConverter::convert)
                .map(operationDto -> operationDto.setCategory(categoryConverter.convert(operationRepository.getCategory(operationDto.getCategoryId()))))
                .orElseThrow(()-> new IllegalArgumentException("No such OperationId"));
    }

    @Transactional
    public OperationDto saveOperationChanges(OperationDto operationDto) {
        checkLimit(operationDto);
        operationRepository.findById(operationDto.getId())
                .map(operation -> updateOperation(operation, operationDto))
                .map(operationRepository::save);
        return operationConverter.convert(operationRepository.findById(operationDto.getId()).orElse(null));
    }

    private Operation updateOperation(Operation operation, OperationDto operationDto) {
        return operation.setAmount(operationDto.getAmount())
            .setCategoryId(operationDto.getCategoryId())
            .setAmount(operationDto.getAmount())
            .setOperationType(operationDto.getOperationType());
    }

    private void checkLimit(OperationDto operationDto) {
        if ((operationDto.getOperationType() == OperationType.CONSUMPTION &&
                operationDto.getAmount() + operationRepository.getWalletConsump(operationDto.getWalletId())
                >= operationRepository.getWalletPayLimit(operationDto.getWalletId()))) {
            operationRepository.setIsExceededFlagToWallet(operationDto.getWalletId());
        }
    }


    public ResponseEntity<Object> deleteOperation(Long id) {
        operationRepository.findById(id)
                .map(operation -> operation.setDeleted(true))
                .map(operationRepository::save)
                .orElseThrow(()-> new IllegalArgumentException("No such OperationId"));
        return ResponseEntity.status(HttpStatus.OK)
                .body(Map.of("success", true, "message", "The operation was successfull!"));
    }
}
