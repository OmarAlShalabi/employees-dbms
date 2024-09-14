package com.cercli.employees.dbms.infrastructure.postgres.mapper;

import com.cercli.employees.dbms.domain.entity.Employee;
import com.cercli.employees.dbms.infrastructure.postgres.entity.PostgresEmployee;
import org.springframework.lang.NonNull;

public class PostgresEmployeeMapper {

    public Employee mapToDomainEntity(final @NonNull PostgresEmployee employee) {
        return null;
    }

    public PostgresEmployee mapToDbEntity(final @NonNull Employee employee) {
        return null;
    }
}
