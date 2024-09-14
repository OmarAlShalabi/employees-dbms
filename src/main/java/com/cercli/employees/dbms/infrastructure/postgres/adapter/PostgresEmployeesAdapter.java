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

public class PostgresEmployeesAdapter implements EmployeeOutputPort {

    private final PostgresEmployeeMapper mapper;
    private final PostgresEmployeesRepository repository;

    public PostgresEmployeesAdapter(final @NonNull PostgresEmployeeMapper mapper,
                                    final @NonNull PostgresEmployeesRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public Optional<Employee> fetchEmployeeById(String empId) {
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
    public Employee persistEmployee(Employee emp) {
        final PostgresEmployee dbEntity = mapper.mapToDbEntity(emp);
        final PostgresEmployee savedEntity = repository.save(dbEntity);
        return mapper.mapToDomainEntity(savedEntity);
    }

    @Override
    public boolean doesEmployeeExist(String empId) {
        return repository.countById(UUID.fromString(empId)) > 0;
    }
}
