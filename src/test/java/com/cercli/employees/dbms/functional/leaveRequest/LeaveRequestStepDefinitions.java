package com.cercli.employees.dbms.functional.leaveRequest;

import com.cercli.employees.dbms.application.port.input.EmployeeInputPort;
import com.cercli.employees.dbms.application.port.input.LeaveRequestInputPort;
import com.cercli.employees.dbms.application.usecase.dto.CreateNewEmployeeCommand;
import com.cercli.employees.dbms.application.usecase.dto.NewLeaveRequestCommand;
import com.cercli.employees.dbms.domain.entity.LeaveRequest;
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

    @When("I create a new Leave Request With Type \\({string}) from current time to next week")
    public void i_create_a_new_leave_request_with_type_from_current_time_to_next_week(final @NonNull String requestType) {
        newRequest = leaveRequestInputPort.createNewLeaveRequest(new NewLeaveRequestCommand(requestType,
                createTestEmployeeAndReturnId(), ZonedDateTime.now(), ZonedDateTime.now().plusDays(7)));
    }

    @Then("I should get new Leave Request With its Id.")
    public void i_should_get_new_leave_request_with_its_id() {
        Assert.assertTrue(newRequest.getRequestId() > 0);
        Assert.assertEquals(7, ChronoUnit.DAYS.between(newRequest.getStartTime(), newRequest.getEndTime()));
    }

    private String createTestEmployeeAndReturnId() {
        final CreateNewEmployeeCommand cmd = new CreateNewEmployeeCommand("test",
                "test@test.com", "test position", "USD", new BigDecimal(100));
        return employeeInputPort.createNewEmployee(cmd).getId();
    }
}
