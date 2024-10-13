package org.example.gestion_cinema.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Entity
@Data @RequiredArgsConstructor
public class Seance implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date heureDebut;
    @OneToOne
    @JoinColumn(name="projection_id")
    private Projection projection;
}
