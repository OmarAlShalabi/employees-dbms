package com.cercli.employees.dbms.application.usecase;

import com.cercli.employees.dbms.application.usecase.dto.CreateNewEmployeeCommand;
import com.cercli.employees.dbms.application.usecase.dto.UpdateEmployeeCommand;
import com.cercli.employees.dbms.domain.entity.Employee;
import org.springframework.lang.NonNull;

import java.util.List;

/**
 * Business use-cases related to Employee Domain Entity
 */
public interface EmployeeUseCase {

    Employee getEmployeeById(final @NonNull String empId);

    List<Employee> getAllEmployees();

    Employee createNewEmployee(final @NonNull CreateNewEmployeeCommand cmd);

    Employee updateEmployeeById(final @NonNull UpdateEmployeeCommand cmd);
}
