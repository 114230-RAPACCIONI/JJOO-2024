package com.tomas.miproyecto.services;

import com.tomas.miproyecto.models.Runner;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RunnerService {

    List<Runner> getAllRunners();

    Runner getRunnerById(Long id);

    Runner createRunner(Runner runner);

    Runner updateRunner(Long id, Runner runner);

    boolean deleteRunner(Long id);
}
