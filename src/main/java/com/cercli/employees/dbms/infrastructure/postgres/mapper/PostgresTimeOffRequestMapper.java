package com.cercli.employees.dbms.infrastructure.postgres.mapper;

import com.cercli.employees.dbms.domain.entity.LeaveRequest;
import com.cercli.employees.dbms.infrastructure.Mapper;
import com.cercli.employees.dbms.infrastructure.postgres.entity.PostgresRequestCategory;
import com.cercli.employees.dbms.infrastructure.postgres.entity.PostgresTimeOffRequest;
import com.cercli.employees.dbms.infrastructure.postgres.repository.PostgresRequestCategoryRepository;
import org.springframework.lang.NonNull;

import java.util.UUID;

public class PostgresTimeOffRequestMapper implements Mapper<LeaveRequest, PostgresTimeOffRequest> {

    private final String zoneId;
    private final PostgresRequestCategoryRepository categoryRepository;

    public PostgresTimeOffRequestMapper(final @NonNull String zoneId,
                                        final @NonNull PostgresRequestCategoryRepository categoryRepository) {
        this.zoneId = zoneId;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public LeaveRequest mapToDomainEntity(final @NonNull PostgresTimeOffRequest dbEntity) {
        return new LeaveRequest(dbEntity.getId(), dbEntity.getRequestCategory().getName(),
                dbEntity.getEmployeeId().toString(), dbEntity.getStartDate(), dbEntity.getEndDate(), zoneId);
    }

    @Override
    public PostgresTimeOffRequest mapToDbEntity(final @NonNull LeaveRequest domainEntity) {
        final PostgresRequestCategory requestCategory = categoryRepository.findByName(domainEntity.getCategory());
        return new PostgresTimeOffRequest(domainEntity.getRequestId(), requestCategory,
                UUID.fromString(domainEntity.getEmployeeId()), domainEntity.getStartTime(), domainEntity.getEndTime());
    }
}
