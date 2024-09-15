package com.cercli.employees.dbms.infrastructure.postgres.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.LastModifiedDate;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Table(name = "employee")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PostgresEmployee {

    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "fullname", nullable = false, length = 200)
    private String fullName;

    @Column(name = "email", nullable = false, length = 256)
    private String email;

    @Column(name = "position", nullable = false, length = 200)
    private String position;

    @Column(name = "salary", nullable = false)
    private BigDecimal salary;

    @Column(name = "currency_code", nullable = false, length = 5)
    private String currencyCode;

    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMPTZ")
    private ZonedDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false, columnDefinition = "TIMESTAMPTZ")
    private ZonedDateTime updatedAt;
}
