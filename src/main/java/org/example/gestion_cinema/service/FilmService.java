package org.example.gestion_cinema.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.example.gestion_cinema.dao.CategoryRepository;
import org.example.gestion_cinema.dao.FilmRepository;
import org.example.gestion_cinema.entities.Category;
import org.example.gestion_cinema.entities.Film;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@Transactional
public class FilmService {
    private final FilmRepository filmRepository;
    private final CategoryRepository categoryRepository;

    public FilmService(FilmRepository filmRepository,CategoryRepository categoryRepository) {
        this.filmRepository = filmRepository;
        this.categoryRepository =categoryRepository;
    }
    public List<Film> getAllFilms(){
        return filmRepository.findAll();
    }
    public Optional<Film> getFilmById(Long id){
        return filmRepository.findById(id);
    }
    public Film addFilm(Film film,Long category_id){
        Optional<Category> categoryOptional= categoryRepository.findById(category_id);
        if(categoryOptional.isEmpty()){
            throw new EntityNotFoundException("Category not found with id " + category_id);
        }
        Category category=categoryOptional.get();
        double[] duree=new double[] {1,2,3,4};
        Film newFilm=new Film();
        newFilm.setTitre(film.getTitre());
        newFilm.setDuree(duree[new Random().nextInt(duree.length)]);
        newFilm.setCategory(category);
        return filmRepository.save(newFilm);
    }
}
