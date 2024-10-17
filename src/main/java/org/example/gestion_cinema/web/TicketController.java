package org.example.gestion_cinema.web;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.example.gestion_cinema.entities.Film;
import org.example.gestion_cinema.entities.Ticket;
import org.example.gestion_cinema.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.rmi.ServerException;
import java.util.List;

@RestController
public class TicketController {
    @Autowired
    private TicketService ticketService;
    @PutMapping(path="/ticket/{tickets_ids}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> reserveTicket(@PathVariable("tickets_ids") List<Long> tickets_ids) throws Exception {
        try{
            ticketService.payerTicket(tickets_ids);
            return ResponseEntity.ok("Your reservation made successfully");
        }catch(EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
