package com.tinkoff.com.tinkoff.financialtracker.utils;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ApiErrorResponse {
    private String errorCode;
    private String errorMessage;
}
