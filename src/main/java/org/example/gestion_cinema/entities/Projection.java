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
    @ManyToOne
    private Seance seance;
    @OneToMany(mappedBy = "projection",cascade = CascadeType.REMOVE)
    private Collection<Ticket> tickets;

    @Override
    public String toString() {
        return "Projection{id=" + id +
            ", dateProjection=" + dateProjection +
            ", prix=" + prix +
            ", salleId=" + (salles != null ? salles.getId() : "null") +
            ", filmId=" + (films != null ? films.getId() : "null") + '}';
    }
}
