package com.atpfx.infrastructure;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.atpfx.model.Tick;

@Repository
public interface TickRepository extends CrudRepository<Tick, Long>{
}
