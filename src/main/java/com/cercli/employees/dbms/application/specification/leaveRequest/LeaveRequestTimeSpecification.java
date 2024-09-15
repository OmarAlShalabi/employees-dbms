package com.cercli.employees.dbms.application.specification.leaveRequest;

import com.cercli.employees.dbms.application.specification.Result;
import com.cercli.employees.dbms.application.specification.Specification;
import com.cercli.employees.dbms.domain.entity.LeaveRequest;
import org.springframework.lang.NonNull;

public class LeaveRequestTimeSpecification implements Specification<LeaveRequest> {

    @Override
    public Result<LeaveRequest> isSatisfiedBy(final @NonNull LeaveRequest obj) {
        if (obj.getStartTime().isAfter(obj.getEndTime())) {
            return new Result<>(obj, true, "start time cannot be after end time!");
        }
        return new Result<>(obj, false, "");
    }
}
