package org.example.gestion_cinema.service;

import jakarta.transaction.Transactional;
import org.example.gestion_cinema.dao.CinemaRepository;
import org.example.gestion_cinema.dao.VilleRepository;
import org.example.gestion_cinema.entities.Cinema;
import org.example.gestion_cinema.entities.Ville;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
@Transactional
public class CinemaService {
    @Autowired
    private CinemaRepository cinemaRepository;

    public List<Cinema> getAllCinemas(){
        return cinemaRepository
            .findAll();
    }
}
