package org.example.gestion_cinema.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@RequiredArgsConstructor
public class Ticket implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomClient;
    private double prix;
    @Column(unique = true,nullable = true)
    private Integer codePayment;
    private boolean reservee;
    @ManyToOne
    private Place place;
    @ManyToOne(cascade = CascadeType.ALL)
    private Projection projection;
}
