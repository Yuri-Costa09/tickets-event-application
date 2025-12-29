package com.yuricosta.ticket_events_application.events.dtos;

import com.yuricosta.ticket_events_application.sessions.Session;
import com.yuricosta.ticket_events_application.sessions.SessionResponseDto;
import com.yuricosta.ticket_events_application.tickets.Ticket;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * DTO for {@link com.yuricosta.ticket_events_application.events.models.Event}
 */
public record EventResponseDto(UUID id, Instant createdAt, Instant updatedAt, String title, String description,
                               List<SessionResponseDto> sessions,
                               List<Ticket> tickets, String imageUrl) implements Serializable {
}