package com.cercli.employees.dbms.infrastructure.postgres.entity;

import com.cercli.employees.dbms.infrastructure.DatabaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Table(name = "time_off_request")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PostgresTimeOffRequest implements DatabaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @ManyToOne
    @JoinColumn(name = "request_category_id", nullable = false)
    private PostgresRequestCategory requestCategory;

    @Column(name = "employee_id", nullable = false)
    private UUID employeeId;

    @Column(name = "start_date", nullable = false, columnDefinition = "TIMESTAMPTZ")
    private ZonedDateTime startDate;

    @Column(name = "end_date", nullable = false, columnDefinition = "TIMESTAMPTZ")
    private ZonedDateTime endDate;
}
