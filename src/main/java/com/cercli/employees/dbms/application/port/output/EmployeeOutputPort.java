package com.cercli.employees.dbms.application.port.output;

import com.cercli.employees.dbms.domain.entity.Employee;
import com.cercli.employees.dbms.domain.entity.Result;
import org.springframework.lang.NonNull;

import java.util.List;

/**
 * Class that defines the methods that will use employee data with external sources (API, DB, etc.)
 */
public class EmployeeOutputPort {

    public Result<Employee> fetchEmployeeById(final @NonNull String empId) {
        return null;
    }

    public List<Employee> fetchAllEmployees() {
        return null;
    }

    public Result<Employee> persistEmployee(final @NonNull Employee emp) {
        return null;
    }
}
