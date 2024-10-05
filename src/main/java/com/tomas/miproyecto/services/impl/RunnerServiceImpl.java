package com.tomas.miproyecto.services.impl;

import com.tomas.miproyecto.entities.RunnerEntity;
import com.tomas.miproyecto.models.Runner;
import com.tomas.miproyecto.repositories.RunnerRepository;
import com.tomas.miproyecto.services.RunnerService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RunnerServiceImpl implements RunnerService {

    @Autowired
    private RunnerRepository runnerRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public List<Runner> getAllRunners() {
        return modelMapper.map(runnerRepository.findAll(), new TypeToken<List<Runner>>() {
        }.getType());
    }

    @Override
    public Runner getRunnerById(Long id) {
        return modelMapper.map(runnerRepository.findById(id).orElse(null), Runner.class);
    }


    @Override
    public Runner createRunner(Runner runner) {
        RunnerEntity runnerEntity = new RunnerEntity();
        runnerEntity.setName(runner.getName());
        runnerRepository.save(runnerEntity);
        return modelMapper.map(runnerEntity, Runner.class);
//        RunnerEntity runnerEntity = modelMapper.map(runner, RunnerEntity.class);
//        RunnerEntity runnerSaved = runnerRepository.save(runnerEntity);
//        return modelMapper.map(runnerSaved, Runner.class);
    }

    @Override
    public Runner updateRunner(Long id, Runner runner) {
        if (runnerRepository.existsById(id)){
            RunnerEntity runnerEntity = modelMapper.map(runner, RunnerEntity.class);
            runnerEntity.setId(id);
            runnerEntity.setName(runner.getName());
            RunnerEntity updateRunner = runnerRepository.save(runnerEntity);
            return modelMapper.map(updateRunner, Runner.class);
        } else {
            return null;
        }
    }

    @Override
    public boolean deleteRunner(Long id) {
        if (runnerRepository.existsById(id)){
            runnerRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
