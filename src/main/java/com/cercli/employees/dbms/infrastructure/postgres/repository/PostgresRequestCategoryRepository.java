package com.cercli.employees.dbms.infrastructure.postgres.repository;

import com.cercli.employees.dbms.infrastructure.postgres.entity.PostgresRequestCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

public interface PostgresRequestCategoryRepository extends JpaRepository<PostgresRequestCategory, Integer> {

    PostgresRequestCategory findByName(final @NonNull String name);
}
