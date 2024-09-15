package com.cercli.employees.dbms.infrastructure.postgres.adapter;

import com.cercli.employees.dbms.application.port.output.LeaveRequestOutputPort;
import com.cercli.employees.dbms.domain.entity.LeaveRequest;
import com.cercli.employees.dbms.infrastructure.postgres.entity.PostgresTimeOffRequest;
import com.cercli.employees.dbms.infrastructure.postgres.mapper.PostgresTimeOffRequestMapper;
import com.cercli.employees.dbms.infrastructure.postgres.repository.PostgresTimeOffRequestRepository;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class PostgresTimeOffRequestAdapter implements LeaveRequestOutputPort {

    private final PostgresTimeOffRequestMapper mapper;
    private final PostgresTimeOffRequestRepository repository;

    public PostgresTimeOffRequestAdapter(final @NonNull PostgresTimeOffRequestMapper mapper,
                                         final @NonNull PostgresTimeOffRequestRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public Optional<LeaveRequest> getLeaveRequestById(final int requestId) {
        final Optional<PostgresTimeOffRequest> optionalDbEntity = repository.findById(requestId);
        return optionalDbEntity.map(mapper::mapToDomainEntity);
    }

    @Override
    public List<LeaveRequest> getLeaveRequestsByEmployeeId(final @NonNull String empId) {
        return repository.findAllByEmployeeId(UUID.fromString(empId)).stream()
                .map(mapper::mapToDomainEntity).collect(Collectors.toList());
    }

    @Override
    public LeaveRequest persistLeaveRequest(final @NonNull LeaveRequest leaveRequest) {
        final PostgresTimeOffRequest savedRequest = repository.save(mapper.mapToDbEntity(leaveRequest));
        return mapper.mapToDomainEntity(savedRequest);
    }

    @Override
    public List<LeaveRequest> getOverlappingLeaveRequests(final @NonNull LeaveRequest leaveRequest) {
        return repository.findOverlappingRequestsByEmployeeIdAndDates(UUID.fromString(leaveRequest.getEmployeeId()),
                leaveRequest.getStartTime(), leaveRequest.getEndTime()).stream()
                .map(mapper::mapToDomainEntity).collect(Collectors.toList());
    }
}
