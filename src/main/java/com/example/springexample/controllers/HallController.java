package com.example.springexample.controllers;

import com.example.springexample.dto.HallDto;
import com.example.springexample.services.HallService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/halls")
@RequiredArgsConstructor
public class HallController {

    private final HallService hallService;

    @GetMapping("/{id}")
    public ResponseEntity<HallDto> getHallById(@PathVariable Integer id) {
        return ResponseEntity.ok(hallService.getById(id));
    }

    @GetMapping
    public ResponseEntity<Collection<HallDto>> getAllHalls() {
        return ResponseEntity.ok(hallService.getAll());
    }

    @PostMapping
    public ResponseEntity<Void> createHall(@RequestBody HallDto hallDto) {
        hallService.create(hallDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateHall(@PathVariable Integer id, @RequestBody HallDto hallDto) {
        hallDto.setId(Long.valueOf(id));
        hallService.update(hallDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHall(@PathVariable Integer id) {
        hallService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}


