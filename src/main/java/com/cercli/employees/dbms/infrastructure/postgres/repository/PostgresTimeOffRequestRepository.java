package com.cercli.employees.dbms.infrastructure.postgres.repository;

import com.cercli.employees.dbms.infrastructure.postgres.entity.PostgresTimeOffRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.UUID;

public interface PostgresTimeOffRequestRepository extends JpaRepository<PostgresTimeOffRequest, Integer> {

    List<PostgresTimeOffRequest> findAllByEmployeeId(final @NonNull UUID empId);
}
