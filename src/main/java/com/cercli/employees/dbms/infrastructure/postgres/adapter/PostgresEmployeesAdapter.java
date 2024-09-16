package com.cercli.employees.dbms.infrastructure.postgres.adapter;

import com.cercli.employees.dbms.application.port.output.EmployeeOutputPort;
import com.cercli.employees.dbms.domain.entity.Employee;
import com.cercli.employees.dbms.infrastructure.postgres.entity.PostgresEmployee;
import com.cercli.employees.dbms.infrastructure.postgres.mapper.PostgresEmployeeMapper;
import com.cercli.employees.dbms.infrastructure.postgres.repository.PostgresEmployeesRepository;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 *
 * Class responsible for interacting with Postgres database to perform actions on Entities.
 */
public class PostgresEmployeesAdapter implements EmployeeOutputPort {

    private final PostgresEmployeeMapper mapper;
    private final PostgresEmployeesRepository repository;

    public PostgresEmployeesAdapter(final @NonNull PostgresEmployeeMapper mapper,
                                    final @NonNull PostgresEmployeesRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public Optional<Employee> fetchEmployeeById(final @NonNull String empId) {
        final Optional<PostgresEmployee> optionalDbEntity = repository.findById(UUID.fromString(empId));
        return optionalDbEntity.map(mapper::mapToDomainEntity);
    }

    @Override
    public List<Employee> fetchAllEmployees() {
        final List<PostgresEmployee> dbEntities = repository.findAll();
        if (dbEntities.isEmpty()) {
            return List.of();
        }
        return dbEntities.stream().map(mapper::mapToDomainEntity).collect(Collectors.toList());
    }

    @Override
    public Employee persistEmployee(final @NonNull Employee emp) {
        final PostgresEmployee dbEntity = mapper.mapToDbEntity(emp);
        final PostgresEmployee savedEntity = repository.save(dbEntity);
        return mapper.mapToDomainEntity(savedEntity);
    }

    @Override
    public boolean doesEmployeeExist(final @NonNull String empId) {
        return repository.countById(UUID.fromString(empId)) > 0;
    }

    @Override
    public boolean isEmailAlreadyUsedBySomeoneElse(final @NonNull String email, final @NonNull String empId) {
        return repository.countEmailsExludingId(email, UUID.fromString(empId)) > 0;
    }
}
