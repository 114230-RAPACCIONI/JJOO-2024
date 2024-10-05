package com.tomas.miproyecto.services.impl;

import com.tomas.miproyecto.dtos.RaceDto;
import com.tomas.miproyecto.dtos.RaceResultDto;
import com.tomas.miproyecto.entities.RaceEntity;
import com.tomas.miproyecto.entities.RaceResultEntity;
import com.tomas.miproyecto.entities.RunnerEntity;
import com.tomas.miproyecto.models.RoundType;
import com.tomas.miproyecto.repositories.RaceRepository;
import com.tomas.miproyecto.repositories.RunnerRepository;
import com.tomas.miproyecto.services.RaceService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class RaceServiceImpl implements RaceService {

    @Autowired
    private RaceRepository raceRepository;

    @Autowired
    private RunnerRepository runnerRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public List<RaceDto> getAllRaces() {
        List<RaceEntity> races = raceRepository.findAll();
        return races.stream()
                .map(race -> modelMapper.map(race, RaceDto.class))
                .toList();
    }

    @Override
    public void saveRaceResults(Long raceId, List<RaceResultDto> results) {
        RaceEntity raceEntity = raceRepository.findById(raceId).orElseThrow(()-> new EntityNotFoundException("Race not found"));
        List<RaceResultEntity> raceResultEntities = results.stream()
                .map(result -> {
                    RaceResultEntity resultEntity = modelMapper.map(result, RaceResultEntity.class);
                    resultEntity.setRace(raceEntity);
                    return resultEntity;
                }).toList();
        raceEntity.setResults(raceResultEntities);
        raceRepository.save(raceEntity);

        // TODO: implementar la logica de clasificacion
        classifyRunners(raceEntity);

    }

    private void classifyRunners(RaceEntity raceEntity) {
        List<RaceResultEntity> results = raceEntity.getResults();
        results.sort(Comparator.comparing(RaceResultEntity::getTime));


    }

    @Override
    public void assignRunnersToHeat() {
        List<RunnerEntity> runners = runnerRepository.findAll();
        Collections.shuffle(runners);
        int heatNumber = 1;
        int lane = 1;

        for (RunnerEntity runner : runners) {
            RaceEntity race = raceRepository.findByRaceNumberAndType(heatNumber, RoundType.HEAT)
                    .orElse(new RaceEntity(null, RoundType.HEAT, heatNumber, new ArrayList<>()));

            RaceResultEntity raceResult = new RaceResultEntity(null, runner, race, lane, 0);
            race.getResults().add(raceResult);

            if (lane == 9) {
                lane = 1;
                heatNumber++;
            } else {
                lane++;
            }

            raceRepository.save(race);
        }
    }
}
