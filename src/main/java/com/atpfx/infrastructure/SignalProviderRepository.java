package com.atpfx.infrastructure;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.atpfx.model.SignalProvider;

@Repository
public interface SignalProviderRepository extends CrudRepository<SignalProvider, Long> {

    SignalProvider findByLabel(String label);
}
