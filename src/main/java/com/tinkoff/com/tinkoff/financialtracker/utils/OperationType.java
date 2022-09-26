package com.tinkoff.com.tinkoff.financialtracker.utils;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum OperationType {
    INCOME("income"),
    CONSUMPTION("consumption");

    private final String name;
}
