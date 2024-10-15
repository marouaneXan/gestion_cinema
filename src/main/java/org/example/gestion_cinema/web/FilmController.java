package org.example.gestion_cinema.web;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.example.gestion_cinema.entities.Film;
import org.example.gestion_cinema.service.FilmService;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.rmi.ServerException;
import java.util.List;
import java.util.Optional;

@RestController
public class FilmController {
    private final FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping("/films")
    public ResponseEntity<List<Film>> getFilms(){
        return new ResponseEntity<>(filmService.getAllFilms(),HttpStatus.OK);
    }
    @GetMapping("/films/{id}")
    public ResponseEntity<Optional<Film>> getFilmById(@PathVariable Long id){
        return new ResponseEntity<>(filmService.getFilmById(id),HttpStatus.OK);
    }
    @PostMapping(path="/films/addFilm/{category_id}",consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Film> addNewFilm(@RequestBody Film film, HttpServletRequest request,@PathVariable Long category_id) throws Exception {
        Film newFilm = filmService.addFilm(film,category_id);
        System.out.println("category is: "+category_id);
        if(newFilm!=null && newFilm.getId() != null){
            URI location = ServletUriComponentsBuilder.fromRequestUri(request)
                .path("/{id}")
                .buildAndExpand(newFilm)
                .toUri();
            return ResponseEntity.created(location).body(newFilm);
        }else{
            throw new ServerException("Try Again :(");
        }
    }

    @PutMapping(path="/films/{id}/category/{category_id}",consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateFilm(@RequestBody Film film, HttpServletRequest request, @PathVariable Long id, @PathVariable Long category_id) throws Exception {
        Film newFilm = filmService.updateFilm(film,id,category_id);
        try{
            if(newFilm!=null){
                return ResponseEntity.ok(newFilm);
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The film is not found or could not be updated");
            }
        }catch(EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while updating the film.");
        }
    }

    @DeleteMapping(path = "/films/{id}")
    public ResponseEntity<?> removeFilm(@PathVariable Long id,HttpServletRequest request){
        try{
            filmService.deleteFilm(id);
            return ResponseEntity.ok("The film deleted successfully");
        }catch(EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
