package com.example.springexample.repositories;

import com.example.springexample.entity.Movie;
import com.example.springexample.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
}
