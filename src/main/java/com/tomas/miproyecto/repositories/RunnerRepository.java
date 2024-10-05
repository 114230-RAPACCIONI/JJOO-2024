package com.tomas.miproyecto.repositories;

import com.tomas.miproyecto.entities.RunnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RunnerRepository extends JpaRepository<RunnerEntity, Long> {

}
