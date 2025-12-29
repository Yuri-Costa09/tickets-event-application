package com.yuricosta.ticket_events_application.sessions;

import com.yuricosta.ticket_events_application.events.models.Event;
import com.yuricosta.ticket_events_application.events.models.TicketBatch;
import com.yuricosta.ticket_events_application.events.valueObjects.Address;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * DTO for {@link Session}
 */
public record SessionResponseDto(UUID id, Instant createdAt, Instant updatedAt, String title, LocalDateTime startTime,
                                 List<TicketBatch> ticketBatches, UUID event_id, Address address) implements Serializable {
}