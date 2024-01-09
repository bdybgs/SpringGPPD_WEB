package com.example.springexample.services;

import com.example.springexample.dto.SessionDto;
import com.example.springexample.entity.Hall;
import com.example.springexample.entity.Session;
import com.example.springexample.repositories.HallRepository;
import com.example.springexample.repositories.SessionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;

@RequiredArgsConstructor
@Slf4j
@Service
public class SessionService implements CRUDService<SessionDto> {

    private final SessionRepository sessionRepository;
    private final HallRepository hallRepository;

    @Override
    public SessionDto getById(Integer id) {
        log.info("Get by ID: " + id);
        Session session = sessionRepository.findById(Long.valueOf(id)).orElseThrow();
        return mapToDto(session);
    }

    @Override
    public Collection<SessionDto> getAll() {
        log.info("Get all");
        return sessionRepository.findAll().stream().map(SessionService::mapToDto).toList();
    }

    @Override
    public void create(SessionDto sessionDto) {
        log.info("Create");
        Session session = mapToEntity(sessionDto, hallRepository);
        sessionRepository.save(session);
    }

    @Override
    public void update(SessionDto sessionDto) {
        log.info("Updated ");
        Session session = mapToEntity(sessionDto, hallRepository);
        sessionRepository.save(session);
    }

    @Override
    public void delete(Integer id) {
        log.info("Delete " + id);
        sessionRepository.deleteById(Long.valueOf(id));
    }

    public static SessionDto mapToDto(Session session) {
        SessionDto sessionDto = new SessionDto();
        sessionDto.setId(session.getId());
        sessionDto.setDate(session.getDate());
        sessionDto.setHallId(session.getHall().getId());
        return sessionDto;
    }

    public static Session mapToEntity(SessionDto sessionDto, HallRepository hallRepository) {
        Session session = new Session();
        session.setId(sessionDto.getId());
        session.setDate(sessionDto.getDate());
        Hall hall = hallRepository.findById(sessionDto.getHallId()).orElseThrow();
        session.setHall(hall);
        return session;
    }
}

