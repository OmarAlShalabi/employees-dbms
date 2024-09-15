package com.cercli.employees.dbms.domain.entity;

import lombok.Getter;
import lombok.ToString;
import org.springframework.lang.NonNull;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Getter
@ToString
public class LeaveRequest implements DomainEntity {

    private final int requestId;
    // Could use Enum for category, but just to save some time on implementing mappers :)
    private final String category;
    private final String employeeId;
    private final ZonedDateTime startTime;
    private final ZonedDateTime endTime;

    public LeaveRequest(final @NonNull int requestId, final @NonNull String category,
                        final @NonNull String employeeId, final @NonNull ZonedDateTime startTime,
                        final @NonNull ZonedDateTime endTime, final @NonNull String zoneId) {
        this.requestId = requestId;
        this.category = category;
        this.employeeId = employeeId;
        this.startTime = startTime.withZoneSameInstant(ZoneId.of(zoneId));
        this.endTime = endTime.withZoneSameInstant(ZoneId.of(zoneId));
    }

    public LeaveRequest(final @NonNull String category,
                        final @NonNull String employeeId, final @NonNull ZonedDateTime startTime,
                        final @NonNull ZonedDateTime endTime) {
        this.requestId = 0;
        this.category = category;
        this.employeeId = employeeId;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
