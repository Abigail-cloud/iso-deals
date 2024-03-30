package com.deals.isodeals.repository;

import com.deals.isodeals.entity.FxEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FxRepository extends JpaRepository<FxEntity, Long> {
    Optional<FxEntity> getIsoEntityByUniqueDealId(String uniqueDealId);
}
