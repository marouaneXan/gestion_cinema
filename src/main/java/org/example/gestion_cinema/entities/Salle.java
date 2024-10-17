package org.example.gestion_cinema.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.Collection;

@Entity
@Data
@RequiredArgsConstructor
public class Salle implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int nombre_places;
    @ManyToOne
    private Cinema cinema;
    @OneToMany(mappedBy = "salle",cascade = CascadeType.ALL)
    private Collection<Place> places;
    @OneToMany(mappedBy = "salles",cascade = CascadeType.ALL)
    private Collection<Projection> projections;

}
