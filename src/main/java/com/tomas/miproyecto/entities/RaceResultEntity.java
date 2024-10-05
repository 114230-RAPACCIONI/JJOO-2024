package com.tomas.miproyecto.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "race_results")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RaceResultEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("runner_id")
    @ManyToOne
    private RunnerEntity runner;

    @JsonProperty("race_id")
    @ManyToOne
    private RaceEntity race;

    private int lane;

    private double time;
}
