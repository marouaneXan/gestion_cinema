package org.example.gestion_cinema.service;

import jakarta.transaction.Transactional;
import org.example.gestion_cinema.dao.*;
import org.example.gestion_cinema.entities.*;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Stream;

@Service
@Transactional
public class ICinemaImpl implements ICinemaInit{
    private final VilleRepository villeRepository;
    private final CinemaRepository cinemaRepository;
    private final CategoryRepository categoryRepository;
    private final FilmRepository filmRepository;
    private final SalleRepository salleRepository;
    private final PlaceRepository placeRepository;
    private final SeanceRepository seanceRepository;
    private final ProjectionRepository projectionRepository;
    private final TicketRepository ticketRepository;

    public ICinemaImpl(VilleRepository villeRepository,CinemaRepository cinemaRepository,CategoryRepository categoryRepository,FilmRepository filmRepository,SalleRepository salleRepository,PlaceRepository placeRepository,SeanceRepository seanceRepository,ProjectionRepository projectionRepository,TicketRepository ticketRepository) {
        this.villeRepository = villeRepository;
        this.cinemaRepository = cinemaRepository;
        this.categoryRepository = categoryRepository;
        this.filmRepository = filmRepository;
        this.salleRepository = salleRepository;
        this.placeRepository = placeRepository;
        this.seanceRepository=seanceRepository;
        this.projectionRepository=projectionRepository;
        this.ticketRepository=ticketRepository;
    }

    @Override
    public void initVilles() {
        Stream.of("Casa","oujda","asfi","Rabat").forEach(
            v->{
                Ville ville=new Ville();
                ville.setName(v);
                villeRepository.save(ville);
            }
        );
    }

    @Override
    public void initCinemas() {
        villeRepository.findAll().forEach(
            v->{
                Stream.of("Megarama","IMAX","FOUNOUN").forEach(
                    c->{
                        Cinema cinema=new Cinema();
                        cinema.setName(c);
                        cinema.setNombre_salles(3+(int)(Math.random()*7));
                        cinema.setVille(v);
                        cinemaRepository.save(cinema);
                    }
                );
            }
        );
    }

    @Override
    public void initFilms() {
        double[] duree=new double[] {1,2,3,4};
        categoryRepository.findAll().forEach(
            c->{
                Stream.of("Inception","Prophet","mega 1","Jhon wick","viking").forEach(
                    f->{
                        Film film = new Film();
                        film.setTitre(f);
                        film.setDuree(duree[new Random().nextInt(duree.length)]);
                        film.setCategory(c);
                        filmRepository.save(film);
                    }
                );
            }
        );
    }

    @Override
    public void initSalles() {
        cinemaRepository.findAll().forEach(
            c->{
                for(int i=0;i<c.getNombre_salles();i++){
                    Salle salle=new Salle();
                    salle.setName("Salle "+i+1);
                    salle.setNombre_places(15+(int)(Math.random()*10));
                    salle.setCinema(c);
                    salleRepository.save(salle);
                }
            }
        );
    }


    @Override
    public void initProjections() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date projectionDate;
        try {
            projectionDate = dateFormat.parse("2024-10-12");
        } catch (ParseException e) {
            e.printStackTrace();
            return;
        }
        villeRepository.findAll().forEach(
            ville->{
                ville.getCinemas().forEach(
                    cinema->{
                        cinema.getSalles().forEach(
                            salle->{
                                filmRepository.findAll().forEach(
                                    film->{
                                        seanceRepository.findAll().forEach(
                                            seance -> {
                                                Projection projection=new Projection();
                                                projection.setDateProjection(projectionDate);
                                                projection.setPrix(10+(int)(Math.random()*7));
                                                projection.setSalles(salle);
                                                projection.setFilms(film);
                                                projection.setSeance(seance);
                                                projectionRepository.save(projection);
                                            }
                                        );
                                    }
                                );
                            }
                        );
                    }
                );
            }
        );
    }

    @Override
    public void initTickets() {
        projectionRepository.findAll().forEach(
            projection ->
                projection.getSalles().getPlaces().forEach(
                place -> {
                    Ticket ticket=new Ticket();
                    ticket.setPlace(place);
                    ticket.setPrix(projection.getPrix());
                    ticket.setReservee(false);
                    ticket.setProjection(projection);
                    ticketRepository.save(ticket);
                }
            )
        );
    }

    @Override
    public void initCategories() {
        Stream.of("Islamic","Dramatic","Fiction","Commedy","Action").forEach(
            c->{
                Category category=new Category();
                category.setName(c);
                categoryRepository.save(category);
            }
        );
    }

    @Override
    public void initPlace() {
        salleRepository.findAll().forEach(
            s->{
                for(int i=0;i<s.getNombre_places();i++){
                    Place place=new Place();
                    place.setNumero(i+1);
                    place.setSalle(s);
                    placeRepository.save(place);
                }
            }
        );
    }

    @Override
    public void initSeance() {
        DateFormat heure = new SimpleDateFormat("HH:mm");
        Stream.of("17:00","17:30","18:00","18:30","19:00","19:30").forEach(
            s->{
                Seance seance=new Seance();
                try {
                    seance.setHeureDebut(heure.parse(s));
                    seanceRepository.save(seance);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }
        );
    }
}
