package com.cercli.employees.dbms.application.specification.employee;

import com.cercli.employees.dbms.application.port.output.EmployeeOutputPort;
import com.cercli.employees.dbms.application.specification.Result;
import com.cercli.employees.dbms.application.specification.Specification;
import com.cercli.employees.dbms.domain.entity.Employee;
import org.springframework.lang.NonNull;

public class UniqueEmailSpecification implements Specification<Employee> {

    private final EmployeeOutputPort employeeOutputPort;

    public UniqueEmailSpecification(final @NonNull EmployeeOutputPort employeeOutputPort) {
        this.employeeOutputPort = employeeOutputPort;
    }

    @Override
    public Result<Employee> isSatisfiedBy(final @NonNull Employee obj) {
        if (employeeOutputPort.isEmailAlreadyUsedBySomeoneElse(obj.getEmail(), obj.getId())) {
            return new Result<>(obj, true, "Email already been taken.");
        }
        return new Result<>(obj, false, "");
    }
}
