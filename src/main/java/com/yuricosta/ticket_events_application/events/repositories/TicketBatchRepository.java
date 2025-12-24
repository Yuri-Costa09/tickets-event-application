package com.yuricosta.ticket_events_application.events.repositories;

import com.yuricosta.ticket_events_application.events.models.TicketBatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TicketBatchRepository extends JpaRepository<TicketBatch, UUID> {
}