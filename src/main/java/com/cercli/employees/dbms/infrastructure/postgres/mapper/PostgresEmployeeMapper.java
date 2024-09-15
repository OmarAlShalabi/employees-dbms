package com.cercli.employees.dbms.infrastructure.postgres.mapper;

import com.cercli.employees.dbms.domain.entity.Employee;
import com.cercli.employees.dbms.infrastructure.postgres.entity.PostgresEmployee;
import org.springframework.lang.NonNull;

import java.util.UUID;

public class PostgresEmployeeMapper {

    private final String zoneId;

    public PostgresEmployeeMapper(String zoneId) {
        this.zoneId = zoneId;
    }

    public Employee mapToDomainEntity(final @NonNull PostgresEmployee employee) {
        return new Employee(employee.getId().toString(), employee.getFullName(), employee.getPosition(),
                employee.getEmail(), employee.getCurrencyCode(), employee.getSalary(), employee.getCreatedAt(),
                employee.getUpdatedAt(), zoneId);
    }

    public PostgresEmployee mapToDbEntity(final @NonNull Employee employee) {
        return new PostgresEmployee(UUID.fromString(employee.getId()), employee.getFullName(),
                employee.getEmail(), employee.getPosition(), employee.getSalary(), employee.getCurrencyCode(),
                employee.getCreatedAt(), employee.getModifiedAt());
    }
}
