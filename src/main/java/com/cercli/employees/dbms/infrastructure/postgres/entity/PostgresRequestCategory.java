package com.cercli.employees.dbms.infrastructure.postgres.entity;

import com.cercli.employees.dbms.infrastructure.DatabaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "request_category")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PostgresRequestCategory implements DatabaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;
}
