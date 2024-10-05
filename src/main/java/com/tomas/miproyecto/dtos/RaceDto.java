package com.tomas.miproyecto.dtos;

import com.tomas.miproyecto.models.RoundType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RaceDto {

    private Long id;

    private RoundType type;

    private int raceNumber;

    private List<RaceResultDto> results;
}
