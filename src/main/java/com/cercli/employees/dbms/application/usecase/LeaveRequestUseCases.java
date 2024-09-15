package com.cercli.employees.dbms.application.usecase;

import com.cercli.employees.dbms.application.usecase.dto.NewLeaveRequestCommand;
import com.cercli.employees.dbms.domain.entity.LeaveRequest;
import org.springframework.lang.NonNull;

import java.util.List;

/**
 * Business use-cases related to Leave Request Domain Entity
 */
public interface LeaveRequestUseCases {

    LeaveRequest createNewLeaveRequest(final @NonNull NewLeaveRequestCommand cmd);

    List<LeaveRequest> getLeaveRequestsByEmployeeId(final @NonNull String empId);

    LeaveRequest getLeaveRequestById(final @NonNull int requestId);
}
