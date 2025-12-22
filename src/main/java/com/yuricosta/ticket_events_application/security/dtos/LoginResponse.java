package com.yuricosta.ticket_events_application.security.dtos;

public record LoginResponse(
        String token,
        Long expiresIn
) {
}
