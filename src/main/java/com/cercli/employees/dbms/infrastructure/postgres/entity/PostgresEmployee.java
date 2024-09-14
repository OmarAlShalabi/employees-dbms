package com.cercli.employees.dbms.infrastructure.postgres.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "employee")
public class PostgresEmployee {

    @Id
    @Column(name = "id", nullable = false)
    private UUID id;
}
