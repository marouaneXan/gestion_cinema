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
import java.util.stream.Collectors;

@Service
@Transactional
public class CinemaService {
    @Autowired
    private CinemaRepository cinemaRepository;
    @Autowired
    private VilleRepository villeRepository;

    public List<Cinema> getAllCinemas() {
        return cinemaRepository
            .findAll();
    }

    public List<Cinema> getCinemasByVille(Long ville_id) {
        Ville existedVille = villeRepository.findById(ville_id).get();
        return getAllCinemas().stream()
            .filter(c -> c.getVille().getId().equals(existedVille.getId()))
            .collect(Collectors.toList());

    }
}
