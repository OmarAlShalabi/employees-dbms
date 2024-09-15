package com.cercli.employees.dbms.infrastructure.postgres.repository;

import com.cercli.employees.dbms.infrastructure.postgres.entity.PostgresEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;

import java.util.UUID;

public interface PostgresEmployeesRepository extends JpaRepository<PostgresEmployee, UUID> {

    long countById(final @NonNull UUID uuid);

    @Query(nativeQuery = true, value = "SELECT COUNT(e.id) FROM employee e WHERE e.email = :email AND e.id <> :empId")
    long countEmailsExludingId(final @NonNull String email, final @NonNull UUID empId);
}
