package com.yuricosta.ticket_events_application.sessions;

import com.yuricosta.ticket_events_application.shared.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/sessions")
public class SessionController {

    private final SessionService sessionService;

    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<SessionResponseDto>> getSessionById(
            @PathVariable("id") UUID id
    ) {

        SessionResponseDto session = sessionService.getSessionById(id);
        ApiResponse<SessionResponseDto> apiResponse = new ApiResponse<>(
                true,
                "Session retrieved successfully",
                session
        );
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<SessionResponseDto>> createSession(
            @Valid @RequestBody CreateSessionDto request
    ) {
        SessionResponseDto session = sessionService.createSession(request);
        ApiResponse<SessionResponseDto> apiResponse = new ApiResponse<>(
                true,
                "Session created successfully",
                session
        );
        return ResponseEntity.status(201).body(apiResponse);
    }
}
