package com.cercli.employees.dbms.functional.leaveRequest;

import com.cercli.employees.dbms.application.port.input.EmployeeInputPort;
import com.cercli.employees.dbms.application.port.input.LeaveRequestInputPort;
import com.cercli.employees.dbms.application.usecase.dto.CreateNewEmployeeCommand;
import com.cercli.employees.dbms.application.usecase.dto.NewLeaveRequestCommand;
import com.cercli.employees.dbms.domain.entity.LeaveRequest;
import com.cercli.employees.dbms.domain.exception.LeaveRequestCreationException;
import com.fasterxml.uuid.Generators;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

public class LeaveRequestStepDefinitions {

    @Autowired
    private LeaveRequestInputPort leaveRequestInputPort;

    @Autowired
    private EmployeeInputPort employeeInputPort;

    private LeaveRequest newRequest;
    private int daysDiff;
    private String failureMessage;
    private String lastEmpId;

    @When("I create a new Leave Request With Type \\({string}) from current time to next \\({int}) days")
    public void i_create_a_new_leave_request_with_type_from_current_time_to_next_week(final @NonNull String requestType,
                                                                                      final @NonNull int days) {
        if (lastEmpId == null) {
            lastEmpId = createTestEmployeeAndReturnId();
        }
        daysDiff = days;
        try {
            newRequest = leaveRequestInputPort.createNewLeaveRequest(new NewLeaveRequestCommand(requestType,
                    lastEmpId, ZonedDateTime.now(), ZonedDateTime.now().plusDays(days)));
        } catch (LeaveRequestCreationException exception) {
            failureMessage = exception.getMessage();
        }
    }

    @Then("I should get new Leave Request With its Id.")
    public void i_should_get_new_leave_request_with_its_id() {
        Assert.assertTrue(newRequest.getRequestId() > 0);
        Assert.assertEquals(daysDiff, ChronoUnit.DAYS.between(newRequest.getStartTime(), newRequest.getEndTime()));
        failureMessage = "";
    }

    private String createTestEmployeeAndReturnId() {
        final CreateNewEmployeeCommand cmd = new CreateNewEmployeeCommand("test",
                Generators.randomBasedGenerator().generate() + "@test.com", "test position", "USD", new BigDecimal(100));
        return employeeInputPort.createNewEmployee(cmd).getId();
    }

    @Then("New Request Should be denied with the following error \\({string})")
    public void newRequestShouldBeDeniedWithTheFollowingError(final @NonNull String errorMessage) {
        Assert.assertEquals(errorMessage, failureMessage);
    }
}
