package com.tomas.miproyecto.repositories;

import com.tomas.miproyecto.entities.RaceResultEntity;
import com.tomas.miproyecto.entities.RunnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RaceResultRepository extends JpaRepository<RaceResultEntity, Long> {
    List<RaceResultEntity> findByRunner(RunnerEntity runner);
}
