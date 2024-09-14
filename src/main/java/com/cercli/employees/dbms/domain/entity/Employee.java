package com.cercli.employees.dbms.domain.entity;

import com.fasterxml.uuid.Generators;
import lombok.Getter;
import org.springframework.lang.NonNull;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Getter
public class Employee implements DomainEntity {

    private final String id;
    private final String fullName;
    private final String position;
    private final String email;
    private final Currency currency;
    private final BigDecimal salary;
    private final ZonedDateTime createdAt;
    private final ZonedDateTime modifiedAt;

    public Employee(final @NonNull String id, final @NonNull String fullName, final @NonNull String position,
                    final @NonNull String email, final @NonNull Currency currency, final @NonNull BigDecimal salary,
                    final @NonNull String zoneId) {
        this.id = id;
        this.fullName = fullName;
        this.position = position;
        this.email = email;
        this.currency = currency;
        this.salary = salary;
        this.createdAt = ZonedDateTime.now(ZoneId.of(zoneId));
        this.modifiedAt = ZonedDateTime.now(ZoneId.of(zoneId));
    }

    public Employee(final @NonNull String fullName, final @NonNull String position,
                    final @NonNull String email, final @NonNull Currency currency, final @NonNull BigDecimal salary,
                    final @NonNull String zoneId) {
        this.id = Generators.timeBasedEpochGenerator().generate().toString();
        this.fullName = fullName;
        this.position = position;
        this.email = email;
        this.currency = currency;
        this.salary = salary;
        this.createdAt = ZonedDateTime.now(ZoneId.of(zoneId));
        this.modifiedAt = ZonedDateTime.now(ZoneId.of(zoneId));
    }
}
