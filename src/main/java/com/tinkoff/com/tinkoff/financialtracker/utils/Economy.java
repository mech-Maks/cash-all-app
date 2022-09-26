package com.tinkoff.com.tinkoff.financialtracker.utils;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class Economy {
    private String balance;
    private String income;
    private String consumption;
}
