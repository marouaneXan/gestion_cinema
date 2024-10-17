package org.example.gestion_cinema;

import org.example.gestion_cinema.service.ICinemaInit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GestionCinemaApplication implements CommandLineRunner {
    @Autowired
    private ICinemaInit iCinemaInit;
	public static void main(String[] args) {
		SpringApplication.run(GestionCinemaApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
//        iCinemaInit.initVilles();
//        iCinemaInit.initCinemas();
//        iCinemaInit.initSalles();
//        iCinemaInit.initPlace();
//        iCinemaInit.initCategories();
//        iCinemaInit.initFilms();
//        iCinemaInit.initSeance();
        iCinemaInit.initProjections();
//        iCinemaInit.initTickets();

    }
}
