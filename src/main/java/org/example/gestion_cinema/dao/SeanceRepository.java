package org.example.gestion_cinema.dao;

import org.example.gestion_cinema.entities.Seance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeanceRepository extends JpaRepository<Seance,Long> {

}
