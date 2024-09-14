package com.cercli.employees.dbms.application.port.output;

import com.cercli.employees.dbms.domain.entity.Employee;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

/**
 * Class that defines the methods that will use employee data with external sources (API, DB, etc.)
 */
public class EmployeeOutputPort {

    public Optional<Employee> fetchEmployeeById(final @NonNull String empId) {
        return null;
    }

    public List<Employee> fetchAllEmployees() {
        return null;
    }

    public Employee persistEmployee(final @NonNull Employee emp) {
        return null;
    }

    public boolean doesEmployeeExist(final @NonNull String empId) {
        return false;
    }
}
