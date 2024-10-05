package com.tomas.miproyecto.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Race {

    private Long id;

    private RoundType type;

    private int raceNumber;

    private List<RaceResult> results;

}
