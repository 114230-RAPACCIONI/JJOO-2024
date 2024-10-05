package com.tomas.miproyecto.repositories;

import com.tomas.miproyecto.entities.RaceResultEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RaceResultRepository extends JpaRepository<RaceResultEntity, Long> {
//    Optional<List<RaceResultEntity>> findByHeat(RaceEntity heat);
}
