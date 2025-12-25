package com.yuricosta.ticket_events_application.events.services;

import com.yuricosta.ticket_events_application.events.dtos.CreateEventRequest;
import com.yuricosta.ticket_events_application.events.dtos.EventResponseDto;
import com.yuricosta.ticket_events_application.events.mappers.EventMapper;
import com.yuricosta.ticket_events_application.events.models.Event;
import com.yuricosta.ticket_events_application.events.repositories.EventRepository;
import com.yuricosta.ticket_events_application.shared.images.ImageStorageService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class EventService {

    private final EventRepository eventRepository;
    private final ImageStorageService imageStorageService;
    private final EventMapper eventMapper;

    public EventService(EventRepository eventRepository, ImageStorageService imageStorageService, EventMapper eventMapper) {
        this.eventRepository = eventRepository;
        this.imageStorageService = imageStorageService;
        this.eventMapper = eventMapper;
    }

    private static final Set<String> ALLOWED = Set.of(
            "image/jpeg",
            "image/png",
            "image/webp"
    );

    public List<EventResponseDto> listAllEvents() {
        log.info("Listing all events");
        List<Event> events = eventRepository.findAll();
        return events.stream()
                .map(eventMapper::toDto)
                .toList();
    }

    @Transactional
    public CompletableFuture<EventResponseDto> createEvent(CreateEventRequest dto, byte[] image, String contentType) {
        log.info("Creating event with title: {}", dto.title());

        // Prevent malicious files.
        if (contentType == null || !ALLOWED.contains(contentType)) {
            log.warn("Invalid image content type: {}", contentType);
            throw new IllegalArgumentException("Invalid image content type");
        }

        return imageStorageService.upload(image, contentType)
                .thenApply(
                        imageUrl -> {
                            Event event = new Event();
                            event.setTitle(dto.title());
                            event.setDescription(dto.description());
                            event.setImageUrl(imageUrl);

                            Event savedEvent = eventRepository.save(event);
                            log.info("Event created with ID: {}", savedEvent.getId());

                            return eventMapper.toDto(savedEvent);
                        }
                );

    }
}
