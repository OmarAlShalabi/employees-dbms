package com.cercli.employees.dbms.application.specification.leaveRequest;

import com.cercli.employees.dbms.application.specification.Result;
import com.cercli.employees.dbms.application.specification.Specification;
import com.cercli.employees.dbms.domain.entity.LeaveRequest;
import org.springframework.lang.NonNull;

import java.util.Set;

public class LeaveRequestTypeSpecification implements Specification<LeaveRequest> {

    private final Set<String> supportedTypes;

    public LeaveRequestTypeSpecification(final @NonNull Set<String> supportedTypes) {
        this.supportedTypes = supportedTypes;
    }

    @Override
    public Result<LeaveRequest> isSatisfiedBy(final @NonNull LeaveRequest obj) {
        if (!supportedTypes.contains(obj.getCategory())) {
            return new Result<>(obj, true, String.format("request type of leave (%s) is not supported, current supported types: %s",
                    obj.getCategory(), supportedTypes));
        }
        return new Result<>(obj, false, "");
    }
}
