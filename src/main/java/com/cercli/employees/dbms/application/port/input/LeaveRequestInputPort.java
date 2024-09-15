package com.cercli.employees.dbms.application.port.input;

import com.cercli.employees.dbms.application.port.output.LeaveRequestOutputPort;
import com.cercli.employees.dbms.application.specification.CompositeSpecification;
import com.cercli.employees.dbms.application.specification.Result;
import com.cercli.employees.dbms.application.usecase.LeaveRequestUseCases;
import com.cercli.employees.dbms.application.usecase.dto.NewLeaveRequestCommand;
import com.cercli.employees.dbms.domain.entity.LeaveRequest;
import com.cercli.employees.dbms.domain.exception.LeaveRequestCreationException;
import com.cercli.employees.dbms.domain.exception.LeaveRequestNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;

@Slf4j
public class LeaveRequestInputPort implements LeaveRequestUseCases {

    private final CompositeSpecification<LeaveRequest> createLeaveRequestSpec;
    private final BiFunction<LeaveRequest, List<LeaveRequest>, Result<LeaveRequest>> overlappingValidator;
    private final LeaveRequestOutputPort outputPort;
    private final boolean isDebug;

    public LeaveRequestInputPort(final @NonNull CompositeSpecification<LeaveRequest> createLeaveRequestSpec,
                                 final @NonNull BiFunction<LeaveRequest, List<LeaveRequest>, Result<LeaveRequest>> overlappingValidator,
                                 final @NonNull LeaveRequestOutputPort outputPort, final @NonNull boolean isDebug) {
        this.createLeaveRequestSpec = createLeaveRequestSpec;
        this.overlappingValidator = overlappingValidator;
        this.outputPort = outputPort;
        this.isDebug = isDebug;
    }

    @Override
    public LeaveRequest createNewLeaveRequest(final @NonNull NewLeaveRequestCommand cmd) {
        final LeaveRequest newLeaveRequest = new LeaveRequest(cmd.getRequestType(), cmd.getEmployeeId(),
                cmd.getStartTime(), cmd.getEndTime());
        final Result<LeaveRequest> validatedLeaveRequest = createLeaveRequestSpec.isSatisfiedBy(newLeaveRequest);
        if (validatedLeaveRequest.isFailed()) {
            throw new LeaveRequestCreationException(validatedLeaveRequest.getErrorMessage());
        }
        final List<LeaveRequest> overlappingLeaveRequests = outputPort.getOverlappingLeaveRequests(newLeaveRequest);
        final Result<LeaveRequest> validatedOverlappingLeaveRequest = overlappingValidator.apply(
                validatedLeaveRequest.getEntity(), overlappingLeaveRequests);
        if (validatedOverlappingLeaveRequest.isFailed()) {
            throw new LeaveRequestCreationException(validatedOverlappingLeaveRequest.getErrorMessage());
        }
        return outputPort.persistLeaveRequest(newLeaveRequest);
    }

    @Override
    public List<LeaveRequest> getLeaveRequestsByEmployeeId(final @NonNull String empId) {
        final List<LeaveRequest> leaveRequests = outputPort.getLeaveRequestsByEmployeeId(empId);
        if (isDebug) {
            log.info(leaveRequests.toString());
        }
        return leaveRequests;
    }

    @Override
    public LeaveRequest getLeaveRequestById(final int requestId) {
        final Optional<LeaveRequest> optionalLeaveRequest = outputPort.getLeaveRequestById(requestId);
        if (optionalLeaveRequest.isEmpty()) {
            throw new LeaveRequestNotFoundException("No leave request exists with Id: " + requestId);
        }
        if (isDebug) {
            log.info(optionalLeaveRequest.get().toString());
        }
        return optionalLeaveRequest.get();
    }
}
