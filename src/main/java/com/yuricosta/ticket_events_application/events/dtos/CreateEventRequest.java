package com.yuricosta.ticket_events_application.events.dtos;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

/**
 * DTO for {@link com.yuricosta.ticket_events_application.events.models.Event}
 */
public record CreateEventRequest(@NotBlank(message = "Title should not be empty.") String title,
                                 @NotBlank(message = "Description should not be empty") String description)
        implements Serializable {
}