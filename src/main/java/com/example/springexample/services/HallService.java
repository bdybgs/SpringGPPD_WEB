package com.example.springexample.services;
import com.example.springexample.dto.HallDto;
import com.example.springexample.entity.Hall;
import com.example.springexample.repositories.HallRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;

@RequiredArgsConstructor
@Slf4j
@Service
public class HallService implements CRUDService<HallDto> {

    private final HallRepository hallRepository;

    @Override
    public HallDto getById(Integer id) {
        log.info("Get by ID: " + id);
        Hall hall = hallRepository.findById(Long.valueOf(id)).orElseThrow();
        return mapToDto(hall);
    }

    @Override
    public Collection<HallDto> getAll() {
        log.info("Get all");
        return hallRepository.findAll().stream().map(HallService::mapToDto).toList();
    }

    @Override
    public void create(HallDto hallDto) {
        log.info("Create");
        Hall hall = mapToEntity(hallDto);
        hallRepository.save(hall);
    }

    @Override
    public void update(HallDto hallDto) {
        log.info("Updated ");
        Hall hall = mapToEntity(hallDto);
        hallRepository.save(hall);
    }

    @Override
    public void delete(Integer id) {
        log.info("Delete " + id);
        hallRepository.deleteById(Long.valueOf(id));
    }

    public static HallDto mapToDto(Hall hall) {
        HallDto hallDto = new HallDto();
        hallDto.setId(hall.getId());
        hallDto.setType(hall.getType());
        return hallDto;
    }

    public static Hall mapToEntity(HallDto hallDto) {
        Hall hall = new Hall();
        hall.setId(hallDto.getId());
        hall.setType(hallDto.getType());
        return hall;
    }
}

