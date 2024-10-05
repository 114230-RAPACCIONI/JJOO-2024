package com.tomas.miproyecto.entities;

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

    @ManyToOne
    private RunnerEntity runner;

    @ManyToOne
    private RaceEntity race;

    private int lane;

    private long time;
}
