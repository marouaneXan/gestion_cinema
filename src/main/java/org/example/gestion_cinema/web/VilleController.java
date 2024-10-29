package org.example.gestion_cinema.web;

import org.example.gestion_cinema.entities.Film;
import org.example.gestion_cinema.entities.Ville;
import org.example.gestion_cinema.service.VilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VilleController {
    @Autowired
    private VilleService villeService;

    @GetMapping("/cities")
    public ResponseEntity<List<Ville>> getCities(){
        return new ResponseEntity<>(villeService.getAllCities(), HttpStatus.OK);
    }
}
