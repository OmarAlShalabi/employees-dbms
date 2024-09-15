package com.cercli.employees.dbms.application.port.input;

import com.cercli.employees.dbms.application.port.output.EmployeeOutputPort;
import com.cercli.employees.dbms.application.usecase.EmployeeUseCase;
import com.cercli.employees.dbms.application.usecase.dto.CreateNewEmployeeCommand;
import com.cercli.employees.dbms.application.usecase.dto.UpdateEmployeeCommand;
import com.cercli.employees.dbms.domain.entity.Employee;
import com.cercli.employees.dbms.application.specification.Result;
import com.cercli.employees.dbms.domain.exception.EmployeeCreationException;
import com.cercli.employees.dbms.domain.exception.EmployeeNotFoundException;
import com.cercli.employees.dbms.application.specification.CompositeSpecification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

/**
 * This class will satisfy the business use-cases by performing actions on domain entities using output ports.
 */
@Slf4j
public class EmployeeInputPort implements EmployeeUseCase {

    private final EmployeeOutputPort employeeOutputPort;
    private final CompositeSpecification<Employee> createEmployeeSpec;
    private final CompositeSpecification<Employee> updateEmployeeSpec;
    private final String zoneId;
    private final boolean isDebug;

    public EmployeeInputPort(final @NonNull EmployeeOutputPort employeeOutputPort,
                             final @NonNull CompositeSpecification<Employee> createEmployeeSpec,
                             final @NonNull CompositeSpecification<Employee> updateEmployeeSpec,
                             final @NonNull String zoneId, boolean isDebug) {
        this.employeeOutputPort = employeeOutputPort;
        this.createEmployeeSpec = createEmployeeSpec;
        this.updateEmployeeSpec = updateEmployeeSpec;
        this.zoneId = zoneId;
        this.isDebug = isDebug;
    }

    @Override
    public Employee getEmployeeById(final @NonNull String empId) {
        final Optional<Employee> optionalEmployee = employeeOutputPort.fetchEmployeeById(empId);
        if (optionalEmployee.isEmpty()) {
            throw new EmployeeNotFoundException("no employee exists with id: " + empId );
        }
        if (isDebug) {
            log.info(optionalEmployee.get().toString());
        }
        return optionalEmployee.get();
    }

    @Override
    public List<Employee> getAllEmployees() {
        final List<Employee> employees = employeeOutputPort.fetchAllEmployees();
        if (isDebug) {
            log.info(employees.toString());
        }
        return employees;
    }

    @Override
    public Employee createNewEmployee(final @NonNull CreateNewEmployeeCommand cmd) {
        final Employee newEmp = new Employee(cmd.getFullName(), cmd.getPosition(), cmd.getEmail(),
                cmd.getCurrencyCode(), cmd.getSalary(), zoneId);
        final Result<Employee> validatedNewEmp = createEmployeeSpec.isSatisfiedBy(newEmp);
        if (validatedNewEmp.isFailed()) {
            throw new EmployeeCreationException(validatedNewEmp.getErrorMessage());
        }
        return employeeOutputPort.persistEmployee(validatedNewEmp.getEntity());
    }

    @Override
    public Employee updateEmployeeById(final @NonNull UpdateEmployeeCommand cmd) {
        final Employee employee = getEmployeeById(cmd.getEmployeeId());
        final Employee updatedEmp = new Employee(cmd.getEmployeeId(), cmd.getFullName(), cmd.getPosition(), cmd.getEmail(),
                cmd.getCurrencyCode(), cmd.getSalary(), employee.getCreatedAt(), zoneId);
        final Result<Employee> validatedNewEmp = updateEmployeeSpec.isSatisfiedBy(updatedEmp);
        if (validatedNewEmp.isFailed()) {
            throw new EmployeeCreationException(validatedNewEmp.getErrorMessage());
        }
        return employeeOutputPort.persistEmployee(validatedNewEmp.getEntity());
    }
}
