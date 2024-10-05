package com.tomas.miproyecto.entities;

import ar.edu.utn.frc.tup.lc.iii.models.RoundType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "races")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RaceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RoundType type;

    private int raceNumber;

    @OneToMany(mappedBy = "race", cascade = CascadeType.ALL)
    private List<RaceResultEntity> results;


}
