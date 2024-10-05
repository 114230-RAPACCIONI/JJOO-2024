package com.tomas.miproyecto.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RaceResultDto {
    private Long id;

    private RunnerDto runner;

    private int lane;

    private Double time;
}
