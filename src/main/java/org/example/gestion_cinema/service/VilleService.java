package org.example.gestion_cinema.service;

import jakarta.transaction.Transactional;
import org.example.gestion_cinema.dao.VilleRepository;
import org.example.gestion_cinema.entities.Film;
import org.example.gestion_cinema.entities.Ville;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class VilleService {
    @Autowired
    private VilleRepository villeRepository;

    public List<Ville> getAllCities(){
        return villeRepository
            .findAll();
    }
}
