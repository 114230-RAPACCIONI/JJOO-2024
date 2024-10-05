package com.tomas.miproyecto.controllers;

import com.tomas.miproyecto.models.Runner;
import com.tomas.miproyecto.services.RunnerService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/runners")
public class RunnerController {

    @Autowired
    private RunnerService runnerService;

    @GetMapping("/getAllRunners")
    public List<Runner> getAllRunners() {
        return runnerService.getAllRunners();
    }

    @GetMapping("/getRunnersById/{id}")
    public ResponseEntity<Runner> getRunnersById(@PathVariable Long id) {
        Runner runner = runnerService.getRunnerById(id);
        if (runner != null){
            return ResponseEntity.ok(runner);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
