package org.example.gestion_cinema.dao;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.Collection;

@Entity
@Data
@RequiredArgsConstructor
public class Projection implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double dateProjection;
    private double prix;
    @ManyToOne
    private Salle salles;
    @ManyToOne
    private Film films;
    @OneToOne(mappedBy = "projection")
    private Seance seance;
    @OneToMany(mappedBy = "projection")
    private Collection<Ticket> tickets;
}
