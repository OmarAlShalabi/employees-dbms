package com.cercli.employees.dbms.application.port.output;

import com.cercli.employees.dbms.domain.entity.Employee;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

/**
 * Class that defines the methods that will use employee data with external sources (API, DB, etc.)
 */
public interface EmployeeOutputPort {

    public Optional<Employee> fetchEmployeeById(final @NonNull String empId);

    public List<Employee> fetchAllEmployees();

    public Employee persistEmployee(final @NonNull Employee emp);

    public boolean doesEmployeeExist(final @NonNull String empId);
}
