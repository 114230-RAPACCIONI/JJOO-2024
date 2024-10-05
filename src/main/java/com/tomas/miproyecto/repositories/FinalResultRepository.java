package com.tomas.miproyecto.repositories;

import com.tomas.miproyecto.entities.FinalResultEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinalResultRepository extends JpaRepository<FinalResultEntity, Long> {

}
