package com.cercli.employees.dbms.application.port.input;

import com.cercli.employees.dbms.application.port.output.EmployeeOutputPort;
import com.cercli.employees.dbms.application.usecase.EmployeeUseCase;
import com.cercli.employees.dbms.application.usecase.dto.CreateNewEmployeeCommand;
import com.cercli.employees.dbms.application.usecase.dto.UpdateEmployeeCommand;
import com.cercli.employees.dbms.domain.entity.Currency;
import com.cercli.employees.dbms.domain.entity.Employee;
import com.cercli.employees.dbms.domain.entity.Result;
import com.cercli.employees.dbms.domain.specification.CompositeSpecification;
import org.springframework.lang.NonNull;

import java.util.List;

/**
 * This class will satisfy the business use-cases by performing actions on domain entities using output ports.
 */
public class EmployeeInputPort implements EmployeeUseCase {

    private final EmployeeOutputPort employeeOutputPort;
    private final CompositeSpecification<Employee> createEmployeeSpec;
    private final CompositeSpecification<Employee> updateEmployeeSpec;
    private final CompositeSpecification<Currency> currencySpec;

    public EmployeeInputPort(final @NonNull EmployeeOutputPort employeeOutputPort,
                             final @NonNull CompositeSpecification<Employee> createEmployeeSpec,
                             final @NonNull CompositeSpecification<Employee> updateEmployeeSpec,
                             final @NonNull CompositeSpecification<Currency> currencySpec) {
        this.employeeOutputPort = employeeOutputPort;
        this.createEmployeeSpec = createEmployeeSpec;
        this.updateEmployeeSpec = updateEmployeeSpec;
        this.currencySpec = currencySpec;
    }

    @Override
    public Employee getEmployeeById(final @NonNull String empId) {

        // get employee from output port
        return null;
    }

    @Override
    public List<Employee> getAllEmployees() {
        // get all employees from output port
        return List.of();
    }

    @Override
    public Result<Employee> createNewEmployee(final @NonNull CreateNewEmployeeCommand cmd) {
        // create entity
        // validate entity
        // persist entity using output port
        return null;
    }

    @Override
    public Result<Employee> updateEmployeeById(final @NonNull UpdateEmployeeCommand cmd) {
        // create entity
        // validate entity
        // persist entity using output port
        return null;
    }
}
