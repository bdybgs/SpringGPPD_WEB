package com.example.springexample.controllers;

import com.example.springexample.dto.SessionDto;
import com.example.springexample.services.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/sessions")
@RequiredArgsConstructor
public class SessionController {

    private final SessionService sessionService;

    @GetMapping("/{id}")
    public ResponseEntity<SessionDto> getSessionById(@PathVariable Integer id) {
        return ResponseEntity.ok(sessionService.getById(id));
    }

    @GetMapping
    public ResponseEntity<Collection<SessionDto>> getAllSessions() {
        return ResponseEntity.ok(sessionService.getAll());
    }

    @PostMapping
    public ResponseEntity<Void> createSession(@RequestBody SessionDto sessionDto) {
        sessionService.create(sessionDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateSession(@PathVariable Integer id, @RequestBody SessionDto sessionDto) {
        sessionDto.setId(Long.valueOf(id));
        sessionService.update(sessionDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSession(@PathVariable Integer id) {
        sessionService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

