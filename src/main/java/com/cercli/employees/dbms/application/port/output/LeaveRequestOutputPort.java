package com.cercli.employees.dbms.application.port.output;

import com.cercli.employees.dbms.domain.entity.LeaveRequest;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

public interface LeaveRequestOutputPort {

    Optional<LeaveRequest> getLeaveRequestById(final int requestId);

    List<LeaveRequest> getLeaveRequestsByEmployeeId(final @NonNull String empId);

    LeaveRequest persistLeaveRequest(final @NonNull LeaveRequest leaveRequest);

    List<LeaveRequest> getOverlappingLeaveRequests(final @NonNull LeaveRequest leaveRequest);
}
