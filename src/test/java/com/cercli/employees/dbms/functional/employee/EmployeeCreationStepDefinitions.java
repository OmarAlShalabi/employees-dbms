package com.cercli.employees.dbms.functional.employee;

import com.cercli.employees.dbms.application.port.input.EmployeeInputPort;
import com.cercli.employees.dbms.application.usecase.dto.CreateNewEmployeeCommand;
import com.cercli.employees.dbms.domain.entity.Employee;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Map;

public class EmployeeCreationStepDefinitions {

    @Autowired
    private EmployeeInputPort port;

    private String newEmployeeId;
    private CreateNewEmployeeCommand cmd;

    @Given("I want to create a new employee with the following Data.")
    public void i_want_to_create_a_new_employee_with_the_following_data(io.cucumber.datatable.DataTable dataTable) {
        final Map<String, String> employeeData = dataTable.asMaps().get(0);
        cmd = new CreateNewEmployeeCommand(employeeData.get("fullName"),
                employeeData.get("email"), employeeData.get("position"), employeeData.get("currencyCode"),
                new BigDecimal(employeeData.get("salary")));
        final Employee employee = port.createNewEmployee(cmd);
        Assert.assertNotNull(employee.getId());
        Assert.assertNotNull(employee.getCreatedAt());
        Assert.assertNotNull(employee.getModifiedAt());
        newEmployeeId = employee.getId();
    }

    @Then("I want to retrieve the newly created employee using their employee Id")
    public void i_Want_To_Retrieve_The_Newly_Created_Employee_Using_Their_Employee_Id() {
        final Employee employee = port.getEmployeeById(newEmployeeId);
        Assert.assertEquals(cmd.getFullName(), employee.getFullName());
    }
}
