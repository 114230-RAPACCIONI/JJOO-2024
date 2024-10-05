package com.tomas.miproyecto.services.impl;

import com.tomas.miproyecto.dtos.RaceDto;
import com.tomas.miproyecto.dtos.RaceResultDto;
import com.tomas.miproyecto.entities.FinalResultEntity;
import com.tomas.miproyecto.entities.RaceEntity;
import com.tomas.miproyecto.entities.RaceResultEntity;
import com.tomas.miproyecto.entities.RunnerEntity;
import com.tomas.miproyecto.models.RoundType;
import com.tomas.miproyecto.repositories.FinalResultRepository;
import com.tomas.miproyecto.repositories.RaceRepository;
import com.tomas.miproyecto.repositories.RaceResultRepository;
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
    private RaceResultRepository raceResultRepository;

    @Autowired
    private FinalResultRepository finalResultRepository;

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
        // ordena los resultados por tiempo
        results.sort(Comparator.comparing(RaceResultEntity::getTime));

        // Clasificación
        List<RunnerEntity> qualifiedRunners = new ArrayList<>();
        int maxQualified = 8; // Puedes ajustar esto según la cantidad que necesites clasificar

        // Agregar los mejores tiempos a la lista de clasificados
        for (int i = 0; i < Math.min(results.size(), maxQualified); i++) {
            RunnerEntity runner = results.get(i).getRunner();
            qualifiedRunners.add(runner);
        }

        // Lógica para almacenar los corredores clasificados, por ejemplo, en una base de datos
        // Aquí puedes crear un método para guardar a los corredores clasificados en la base de datos
        saveQualifiedRunners(qualifiedRunners);

    }

    private void saveQualifiedRunners(List<RunnerEntity> qualifiedRunners) {
        List<FinalResultEntity> finalResults = new ArrayList<>();

        for (RunnerEntity runner : qualifiedRunners) {
            // Crear una nueva instancia de FinalResultEntity
            FinalResultEntity finalResult = new FinalResultEntity();
            finalResult.setRunner(runner);
            // Obtener el tiempo final del corredor
            finalResult.setTime(getRunnerFinalTime(runner));

            finalResults.add(finalResult);
        }

        // Guardar todos los resultados finales en la base de datos
        finalResultRepository.saveAll(finalResults);
    }

    private double getRunnerFinalTime(RunnerEntity runner) {
        // Buscar los resultados del corredor en la base de datos
        List<RaceResultEntity> results = raceResultRepository.findByRunner(runner);

        // Comprobar si hay resultados
        if (results.isEmpty()) {
            return 0.0; // o lanzar una excepción si no hay resultados
        }

        // Obtener el resultado más reciente (puedes usar una propiedad de tiempo o ID)
        RaceResultEntity latestResult = results.stream()
                .max(Comparator.comparing(RaceResultEntity::getTime)) // o el criterio que determines para obtener el más reciente
                .orElseThrow(() -> new EntityNotFoundException("No se encontró el resultado para el corredor"));

        // Devolver el tiempo del resultado más reciente
        return latestResult.getTime();
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
