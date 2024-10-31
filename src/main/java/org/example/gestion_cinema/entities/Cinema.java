package org.example.gestion_cinema.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.Collection;

@Entity
@Data
@RequiredArgsConstructor
public class Cinema implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double longitude,latitude,altitude;
    private int nombre_salles;
    @OneToMany(mappedBy = "cinema",cascade = CascadeType.REMOVE)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<Salle> salles;
    @ManyToOne
    private Ville ville;

    @Override
    public String toString() {
        return "Cinema{id=" + id + ", name='" + name + '\'' +
            ", sallesCount=" + (salles != null ? salles.size() : 0) + '}'; // Avoid printing Salles directly
    }
}
