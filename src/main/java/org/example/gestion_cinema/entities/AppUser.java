package org.example.gestion_cinema.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class AppUser {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    //LAZY c'est a dire quand je charge une objet AppUser il ne va pas chargé automatiquement les roles
    //EAGER c'est a dire quand je charge une objet AppUser il va chargé automatiquement les roles
    //quand on va travaille avec EAGER il est preferable d'initialiser avec une array liste vide;
    @JoinTable(
        name = "user_roles",
        joinColumns = @JoinColumn(name="user_id"),
        inverseJoinColumns = @JoinColumn(name="role_id")
    )
    private Collection<AppRole> roles=new ArrayList<>();
}
