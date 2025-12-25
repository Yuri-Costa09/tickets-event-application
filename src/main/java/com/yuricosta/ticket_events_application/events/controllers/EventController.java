package com.yuricosta.ticket_events_application.events.controllers;

import com.yuricosta.ticket_events_application.events.dtos.CreateEventRequest;
import com.yuricosta.ticket_events_application.events.dtos.EventResponseDto;
import com.yuricosta.ticket_events_application.events.services.EventService;
import com.yuricosta.ticket_events_application.shared.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
                responseCode = "201",
                description = "Event created successfully."
        )
})
@RestController
@RequestMapping("api/v1/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<EventResponseDto>>> listAll() {
        List<EventResponseDto> events = eventService.listAllEvents();
        ApiResponse<List<EventResponseDto>> apiResponse = new ApiResponse<>(
                true,
                "Events retrieved successfully",
                events
        );
        return ResponseEntity.ok(apiResponse);
    }

    @Operation(summary = "Criar evento com arquivo")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public CompletableFuture<ResponseEntity<ApiResponse<EventResponseDto>>> createEvent(
            @Parameter(
                    description = "JSON do evento",
                    content = @Content(mediaType = "application/json")
            )
            @Valid @RequestPart("event") CreateEventRequest request,
            @RequestPart("file") MultipartFile file
    ) throws IOException {

        return eventService.createEvent(request, file.getBytes(), file.getContentType())
                .thenApply(eventResponseDto -> {
                    ApiResponse<EventResponseDto> apiResponse = new ApiResponse<>(
                            true,
                            "Event created successfully",
                            eventResponseDto
                    );
                    return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
                });
    }
}
