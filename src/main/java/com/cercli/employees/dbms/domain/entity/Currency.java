package com.cercli.employees.dbms.domain.entity;

import lombok.Getter;
import org.springframework.lang.NonNull;

@Getter
public class Currency implements DomainEntity{

    private final String code;
    private final String Name;
    private final int decimals;

    public Currency(final @NonNull  String code, final @NonNull String name, final @NonNull int decimals) {
        this.code = code;
        Name = name;
        this.decimals = decimals;
    }
}
