package com.cercli.employees.dbms.infrastructure.postgres.repository;

import com.cercli.employees.dbms.infrastructure.postgres.entity.PostgresTimeOffRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

public interface PostgresTimeOffRequestRepository extends JpaRepository<PostgresTimeOffRequest, Integer> {

    List<PostgresTimeOffRequest> findAllByEmployeeId(final @NonNull UUID empId);

    /**
     * This retrieves all overlapping intervals that covers one of these cases:
     * 1- interval is completely overlapping with the new interval
     * 2- interval overlaps from right with the new interval
     * 3- interval overlaps from left with the new interval
     * @param empId Employee id
     * @param startTime Start Time of the request
     * @param endTime End Time of the request
     * @return overlapping intervals
     */
    @Query(nativeQuery = true, value = "SELECT * FROM time_off_request r WHERE r.employee_id = :empId AND (" +
            "(r.start_date >= :startTime AND r.end_date <= :endTime)" +
            " OR (r.end_date > :startTime AND r.start_date <= :startTime)" +
            " OR (r.start_date < :endTime AND r.end_date >= :endTime))")
    List<PostgresTimeOffRequest> findOverlappingRequestsByEmployeeIdAndDates(final @NonNull UUID empId,
                                                                             final @NonNull ZonedDateTime startTime,
                                                                             final @NonNull ZonedDateTime endTime);
}
