package org.example.gestion_cinema.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.example.gestion_cinema.entities.Film;

import java.io.Serializable;
import java.util.Collection;

@Entity
@Data
@RequiredArgsConstructor
public class Category implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 75)
    private String name;
    @OneToMany(mappedBy = "category")
    private Collection<Film> films;
}
