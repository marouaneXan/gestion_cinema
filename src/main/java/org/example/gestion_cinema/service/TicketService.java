package org.example.gestion_cinema.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.example.gestion_cinema.dao.PlaceRepository;
import org.example.gestion_cinema.dao.ProjectionRepository;
import org.example.gestion_cinema.dao.TicketRepository;
import org.example.gestion_cinema.entities.Place;
import org.example.gestion_cinema.entities.Projection;
import org.example.gestion_cinema.entities.Ticket;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class TicketService {
    private final TicketRepository ticketRepository;
    private final ProjectionRepository projectionRepository;
    private final PlaceRepository placeRepository;
    public TicketService(TicketRepository ticketRepository, ProjectionRepository projectionRepository, PlaceRepository placeRepository) {
        this.ticketRepository = ticketRepository;
        this.projectionRepository = projectionRepository;
        this.placeRepository = placeRepository;
    }

    public Ticket payerTicket(Ticket newTicket,Long projection_id,Long place_id) {
        Projection projection=projectionRepository.findById(projection_id).orElseThrow(EntityNotFoundException::new);
        Place place=placeRepository.findById(place_id).orElseThrow(()-> new EntityNotFoundException("Place not found"));
        Ticket ticket=new Ticket();
        ticket.setProjection(projection);
        ticket.setPlace(place);
        ticket.setNomClient(newTicket.getNomClient());
        ticket.setPrix(newTicket.getPrix());
        ticketRepository.save(ticket);
        return ticket;
    }
}
