package com.wora.common.infrastructure.persistence;

import com.wora.contract.domain.exceptions.ContractNotFoundException;

import java.util.List;
import java.util.Optional;

public interface BaseRepository<Entity, ID> {

    List<Entity> findAll();

    Optional<Entity> findById(ID id);

    void create(Entity entity);

    void update(ID id, Entity entity) throws ContractNotFoundException;

    void delete(ID id);

    Boolean existsById(ID id);

    Optional<Entity> findByColumn(String columnName, String value);
}
