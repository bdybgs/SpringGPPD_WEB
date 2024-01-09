package com.example.springexample.repositories;

import com.example.springexample.entity.Session;
import com.example.springexample.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
}