package com.yuricosta.ticket_events_application.events.dtos;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * DTO for {@link com.yuricosta.ticket_events_application.events.models.Event}
 */
public record EventResponseDto(UUID id, Instant createdAt, Instant updatedAt, String title, String description,
                               List<SessionDto> sessions,
                               List<TicketDto> tickets, String imageUrl) implements Serializable {
    /**
     * DTO for {@link com.yuricosta.ticket_events_application.events.models.Session}
     */
    public record SessionDto(UUID id, Instant createdAt, Instant updatedAt, String title,
                             LocalDateTime startTime) implements Serializable {
    }

    /**
     * DTO for {@link com.yuricosta.ticket_events_application.tickets.Ticket}
     */
    public record TicketDto(UUID id, Instant createdAt, Instant updatedAt, UUID userId, String qrCode, UUID eventId,
                            UUID batchId) implements Serializable {
    }
}