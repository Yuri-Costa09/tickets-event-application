package com.yuricosta.ticket_events_application.sessions;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO for {@link Session}
 */
public record CreateSessionDto(
        @NotBlank(message = "Title should not be empty.") String title,
        @NotNull @Future(message = "Start Time should be in the future.") LocalDateTime startTime,
        @NotNull(message = "Event Id should not be empty.") UUID event_id,
        @NotBlank(message = "Street should not be empty") String addressStreet,
        @NotBlank(message = "Number should not be empty") String addressNumber,
        @NotBlank(message = "Neighborhood should not be empty.") String addressNeighborhood,
        @NotBlank(message = "City should not be empty.") String addressCity,
        @NotBlank(message = "Zipcode should not be empty.") String addressZipCode,
        @NotBlank(message = "State should not be empty.") String addressState) implements Serializable {
}