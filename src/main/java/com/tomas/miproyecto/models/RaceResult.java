package com.tomas.miproyecto.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RaceResult {

    private Long id;

    private Runner runner;

    private Race race;

    private int lane;

    private Double time;
}
