package com.cercli.employees.dbms.application.specification.leaveRequest;

import com.cercli.employees.dbms.application.specification.Result;
import com.cercli.employees.dbms.domain.entity.LeaveRequest;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;

public class OverlappingRequestsSpecification implements BiFunction<LeaveRequest, List<LeaveRequest>, Result<LeaveRequest>> {

    private final Map<String, Set<String>> overlappingRequestTypes;

    public OverlappingRequestsSpecification(final @NonNull Map<String, Set<String>> overlappingRequestTypes) {
        this.overlappingRequestTypes = overlappingRequestTypes;
    }

    @Override
    public Result<LeaveRequest> apply(final @NonNull LeaveRequest leaveRequest, final @NonNull List<LeaveRequest> overlappingRequests) {
        for (final LeaveRequest overlappingRequest : overlappingRequests) {
            if (!overlappingRequestTypes.containsKey(leaveRequest.getCategory())) {
                return new Result<>(leaveRequest, true, String.format("cannot overlap request of type (%s) with other types of leaves",
                        leaveRequest.getCategory()));
            }
            if (!overlappingRequestTypes.get(leaveRequest.getCategory()).contains(overlappingRequest.getCategory())) {
                return new Result<>(leaveRequest, true, String.format("cannot overlap request of type (%s) with already existing request with type (%s)",
                        leaveRequest.getCategory(), overlappingRequest.getCategory()));
            }
        }
        return new Result<>(leaveRequest, false, "");
    }
}
