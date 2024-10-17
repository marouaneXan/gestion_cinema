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

import java.util.Arrays;
import java.util.List;

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

    public void payerTicket(List<Long> tickets_ids) {
        tickets_ids.forEach(
            ticket_id->{
                Ticket ticket=ticketRepository
                    .findById(ticket_id)
                    .orElseThrow(()->new EntityNotFoundException("ticket not found"));
                ticket.setReservee(true);
                ticketRepository.save(ticket);
            }
        );
    }
}
