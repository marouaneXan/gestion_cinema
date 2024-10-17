package org.example.gestion_cinema.web;

import jakarta.servlet.http.HttpServletRequest;
import org.example.gestion_cinema.entities.Film;
import org.example.gestion_cinema.entities.Ticket;
import org.example.gestion_cinema.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.rmi.ServerException;

@RestController
public class TicketController {
    @Autowired
    private TicketService ticketService;
    @PostMapping(path="/ticket/{projection_id}/{place_id}",consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Ticket> reserveTicket(@RequestBody Ticket newTicket, HttpServletRequest request, @PathVariable Long projection_id,@PathVariable Long place_id) throws Exception {
        Ticket ticket=ticketService.payerTicket(newTicket,projection_id,place_id);
        if(ticket!=null && ticket.getId() != null){
            URI location = ServletUriComponentsBuilder.fromRequestUri(request)
                    .path("/{id}")
                    .buildAndExpand(ticket)
                    .toUri();
            return ResponseEntity.created(location).body(ticket);
        }else{
            throw new ServerException("Try Again :(");
        }
    }
}
