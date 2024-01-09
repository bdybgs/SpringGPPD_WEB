package com.example.springexample.controllers;

import com.example.springexample.dto.TicketDto;
import com.example.springexample.services.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @GetMapping("/{id}")
    public ResponseEntity<TicketDto> getTicketById(@PathVariable Integer id) {
        return ResponseEntity.ok(ticketService.getById(id));
    }

    @GetMapping
    public ResponseEntity<Collection<TicketDto>> getAllTickets() {
        return ResponseEntity.ok(ticketService.getAll());
    }

    @PostMapping
    public ResponseEntity<Void> createTicket(@RequestBody TicketDto ticketDto) {
        ticketService.create(ticketDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateTicket(@PathVariable Integer id, @RequestBody TicketDto ticketDto) {
        ticketDto.setId(Long.valueOf(id));
        ticketService.update(ticketDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Integer id) {
        ticketService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
