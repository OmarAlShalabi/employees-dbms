package com.cercli.employees.dbms.infrastructure.postgres.repository;

import com.cercli.employees.dbms.infrastructure.postgres.entity.PostgresEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.UUID;

public interface PostgresEmployeesRepository extends JpaRepository<PostgresEmployee, UUID> {

    long countById(final @NonNull UUID uuid);
}
