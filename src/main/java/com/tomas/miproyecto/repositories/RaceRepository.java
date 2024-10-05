package com.tomas.miproyecto.repositories;

import com.tomas.miproyecto.entities.RaceEntity;
import com.tomas.miproyecto.models.RoundType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RaceRepository extends JpaRepository<RaceEntity, Long> {

    Optional<RaceEntity> findByRaceNumberAndType(int heatNumber, RoundType roundType);
}
