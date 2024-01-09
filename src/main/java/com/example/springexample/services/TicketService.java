package com.example.springexample.services;

import com.example.springexample.dto.TicketDto;
import com.example.springexample.entity.Movie;
import com.example.springexample.entity.Session;
import com.example.springexample.entity.Ticket;
import com.example.springexample.repositories.SessionRepository;
import com.example.springexample.repositories.MovieRepository;
import com.example.springexample.repositories.TicketRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;

@RequiredArgsConstructor
@Slf4j
@Service
public class TicketService implements CRUDService<TicketDto> {

    private final TicketRepository ticketRepository;
    private final SessionRepository sessionRepository;
    private final MovieRepository movieRepository;

    @Override
    public TicketDto getById(Integer id) {
        log.info("Get by ID: " + id);
        Ticket ticket = ticketRepository.findById(Long.valueOf(id)).orElseThrow();
        return mapToDto(ticket);
    }

    @Override
    public Collection<TicketDto> getAll() {
        log.info("Get all");
        return ticketRepository.findAll().stream().map(TicketService::mapToDto).toList();
    }

    @Override
    public void create(TicketDto ticketDto) {
        log.info("Create");
        Ticket ticket = mapToEntity(ticketDto, sessionRepository, movieRepository);
        ticketRepository.save(ticket);
    }

    @Override
    public void update(TicketDto ticketDto) {
        log.info("Updated ");
        Ticket ticket = mapToEntity(ticketDto, sessionRepository, movieRepository);
        ticketRepository.save(ticket);
    }

    @Override
    public void delete(Integer id) {
        log.info("Delete " + id);
        ticketRepository.deleteById(Long.valueOf(id));
    }

    public static TicketDto mapToDto(Ticket ticket) {
        TicketDto ticketDto = new TicketDto();
        ticketDto.setId(ticket.getId());
        ticketDto.setDate(ticket.getDate());
        ticketDto.setDuration(ticket.getDuration());
        ticketDto.setMovieId(ticket.getMovie().getId());
        ticketDto.setSessionId(ticket.getSession().getId());
        return ticketDto;
    }

    public static Ticket mapToEntity(TicketDto ticketDto, SessionRepository sessionRepository, MovieRepository movieRepository) {
        Ticket ticket = new Ticket();
        ticket.setId(ticketDto.getId());
        ticket.setDate(ticketDto.getDate());
        ticket.setDuration(ticketDto.getDuration());

        Session session = sessionRepository.findById(ticketDto.getSessionId()).orElseThrow();
        Movie movie = movieRepository.findById(ticketDto.getMovieId()).orElseThrow();

        ticket.setSession(session);
        ticket.setMovie(movie);

        return ticket;
    }
}
