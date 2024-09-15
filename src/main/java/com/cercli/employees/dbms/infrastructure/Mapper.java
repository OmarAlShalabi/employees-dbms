package com.cercli.employees.dbms.infrastructure;

import com.cercli.employees.dbms.domain.entity.DomainEntity;
import org.springframework.lang.NonNull;

public interface Mapper<K extends DomainEntity,V extends DatabaseEntity> {

    K mapToDomainEntity(final @NonNull V dbEntity);

    V mapToDbEntity(final @NonNull K domainEntity);
}
