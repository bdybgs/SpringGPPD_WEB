package com.example.springexample.services;

import com.example.springexample.dto.MovieDto;
import com.example.springexample.entity.Movie;
import com.example.springexample.repositories.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;

@RequiredArgsConstructor
@Slf4j
@Service
public class MovieService implements CRUDService<MovieDto> {

    private final MovieRepository movieRepository;

    @Override
    public MovieDto getById(Integer id) {
        log.info("Get by ID: " + id);
        Movie movie = movieRepository.findById(Long.valueOf(id)).orElseThrow();
        return mapToDto(movie);
    }

    @Override
    public Collection<MovieDto> getAll() {
        log.info("Get all");
        return movieRepository.findAll().stream().map(MovieService::mapToDto).toList();
    }

    @Override
    public void create(MovieDto movieDto) {
        log.info("Create");
        Movie movie = mapToEntity(movieDto);
        movieRepository.save(movie);
    }

    @Override
    public void update(MovieDto movieDto) {
        log.info("Updated ");
        Movie movie = mapToEntity(movieDto);
        movieRepository.save(movie);
    }

    @Override
    public void delete(Integer id) {
        log.info("Delete " + id);
        movieRepository.deleteById(Long.valueOf(id));
    }

    public static MovieDto mapToDto(Movie movie) {
        MovieDto movieDto = new MovieDto();
        movieDto.setId(movie.getId());
        movieDto.setTitle(movie.getTitle());
        movieDto.setDuration(movie.getDuration());
        return movieDto;
    }

    public static Movie mapToEntity(MovieDto movieDto) {
        Movie movie = new Movie();
        movie.setId(movieDto.getId());
        movie.setTitle(movieDto.getTitle());
        movie.setDuration(movieDto.getDuration());
        return movie;
    }
}
