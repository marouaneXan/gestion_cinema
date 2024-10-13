package org.example.gestion_cinema.dao;

import org.example.gestion_cinema.entities.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceRepository extends JpaRepository<Place,Long> {

}
