package com.tomas.miproyecto.controllers;

import com.tomas.miproyecto.dtos.RaceDto;
import com.tomas.miproyecto.dtos.RaceResultDto;
import com.tomas.miproyecto.services.RaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/races")
public class RaceController {

    @Autowired
    private RaceService raceService;

    @GetMapping
    public List<RaceDto> getAllRaces() {
        return raceService.getAllRaces();
    }

    @PostMapping("/{raceId}/results")
    public ResponseEntity<Void> saveRaceResults(@PathVariable Long raceId, @RequestBody List<RaceResultDto> results) {
        raceService.saveRaceResults(raceId, results);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/assignRunners")
    public ResponseEntity<Void> assignRunnersToHeats() {
        raceService.assignRunnersToHeat();
        return ResponseEntity.ok().build();
    }
}
