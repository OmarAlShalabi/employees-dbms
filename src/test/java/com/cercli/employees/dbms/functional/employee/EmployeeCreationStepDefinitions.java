package com.cercli.employees.dbms.functional.employee;

import com.cercli.employees.dbms.application.port.input.EmployeeInputPort;
import com.cercli.employees.dbms.application.usecase.dto.CreateNewEmployeeCommand;
import com.cercli.employees.dbms.application.usecase.dto.UpdateEmployeeCommand;
import com.cercli.employees.dbms.domain.entity.Employee;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.testcontainers.shaded.org.awaitility.Awaitility;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class EmployeeCreationStepDefinitions {

    @Autowired
    private EmployeeInputPort port;

    private String newEmployeeId;
    private CreateNewEmployeeCommand createCmd;
    private UpdateEmployeeCommand updateCmd;

    @When("I want to create a new employee with the following Data.")
    public void i_want_to_create_a_new_employee_with_the_following_data(io.cucumber.datatable.DataTable dataTable) {
        final Map<String, String> employeeData = dataTable.asMaps().get(0);
        createCmd = new CreateNewEmployeeCommand(employeeData.get("fullName"),
                employeeData.get("email"), employeeData.get("position"), employeeData.get("currencyCode"),
                new BigDecimal(employeeData.get("salary")));
        final Employee employee = port.createNewEmployee(createCmd);
        Assert.assertNotNull(employee.getId());
        Assert.assertNotNull(employee.getCreatedAt());
        Assert.assertNotNull(employee.getModifiedAt());
        newEmployeeId = employee.getId();
    }

    @When("I want to edit the previous employee information to have a salary of \\({int})")
    public void iWantToEditThePreviousEmployeeInformationToHaveASalaryOf(int newSalary) {
        Awaitility.await().atMost(2, TimeUnit.SECONDS);
        updateCmd = new UpdateEmployeeCommand(newEmployeeId, createCmd.getFullName(),
                createCmd.getEmail(), createCmd.getPosition(), createCmd.getCurrencyCode(), new BigDecimal(newSalary));
        port.updateEmployeeById(updateCmd);
    }

    @Then("I want to retrieve the newly created employee using their employee Id, and check updated salary info, and last modified date.")
    public void i_Want_To_Retrieve_The_Newly_Created_Employee_Using_Their_Employee_Id() {
        final Employee employee = port.getEmployeeById(newEmployeeId);
        Assert.assertEquals(createCmd.getFullName(), employee.getFullName());
        Assert.assertEquals(updateCmd.getSalary().toString(), employee.getSalary().toString());
        Assert.assertNotEquals(employee.getCreatedAt().toString(), employee.getModifiedAt().toString());
    }
}
