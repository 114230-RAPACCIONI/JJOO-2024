package com.tomas.miproyecto.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "final_results")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FinalResultEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private RunnerEntity runner;

    private double time;
}
