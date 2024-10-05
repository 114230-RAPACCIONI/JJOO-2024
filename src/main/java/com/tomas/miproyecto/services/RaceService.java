package com.tomas.miproyecto.services;

import com.tomas.miproyecto.dtos.RaceDto;
import com.tomas.miproyecto.dtos.RaceResultDto;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface RaceService {
    List<RaceDto> getAllRaces();
    void saveRaceResults(Long raceId, List<RaceResultDto> results);
    void assignRunnersToHeat();
}
