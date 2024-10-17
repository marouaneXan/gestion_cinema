package org.example.gestion_cinema.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Entity
@Data
@RequiredArgsConstructor
public class Projection implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date dateProjection;
    private double prix;
    @ManyToOne
    private Salle salles;
    @ManyToOne
    private Film films;
    @OneToOne(mappedBy = "projection")
    private Seance seance;
    @OneToMany(mappedBy = "projection",cascade = CascadeType.REMOVE)
    private Collection<Ticket> tickets;
}
