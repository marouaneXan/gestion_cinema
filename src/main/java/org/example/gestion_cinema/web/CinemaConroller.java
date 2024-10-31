package org.example.gestion_cinema.web;


import org.example.gestion_cinema.entities.Cinema;
import org.example.gestion_cinema.service.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CinemaConroller {
    @Autowired
    private CinemaService cinemaService;

    @GetMapping("/cinemas")
    public ResponseEntity<List<Cinema>> getCinema(){
        return new ResponseEntity<>(cinemaService.getAllCinemas(), HttpStatus.OK);
    }

    @GetMapping("/cinemas/city/{ville_id}")
    public ResponseEntity<List<Cinema>> getCinemaByCities(@PathVariable Long ville_id){
        return new ResponseEntity<>(cinemaService.getCinemasByVille(ville_id), HttpStatus.OK);
    }
}
