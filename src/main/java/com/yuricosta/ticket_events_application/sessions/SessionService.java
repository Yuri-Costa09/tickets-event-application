package com.yuricosta.ticket_events_application.sessions;

import com.yuricosta.ticket_events_application.events.repositories.EventRepository;
import com.yuricosta.ticket_events_application.events.valueObjects.Address;
import com.yuricosta.ticket_events_application.shared.errors.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class SessionService {

    private final SessionRepository sessionRepository;
    private final EventRepository eventRepository;
    private final SessionMapper sessionMapper;

    public SessionService(SessionRepository sessionRepository, EventRepository eventRepository, SessionMapper sessionMapper) {
        this.sessionRepository = sessionRepository;
        this.eventRepository = eventRepository;
        this.sessionMapper = sessionMapper;
    }

    public SessionResponseDto getSessionById(UUID sessionId) {
        log.info("Retrieving session with ID: {}", sessionId);
        Session session = sessionRepository.findById(sessionId).orElseThrow(
                () -> {
                    log.warn("Session not found with ID: {}", sessionId);
                    return new NotFoundException("Session not found");
                }
        );
        return sessionMapper.toDto(session);
    }

    @Transactional
    public SessionResponseDto createSession(CreateSessionDto dto) {
        log.info("Creating session for eventId: {}", dto.event_id());

        var event = eventRepository.findById(dto.event_id()).orElseThrow(
                () -> {
                    log.warn("Event not found with id: {}", dto.event_id());
                    return new NotFoundException("Event not found");
                }
        );

        Address address = Address.of(
                dto.addressStreet(),
                dto.addressNumber(),
                dto.addressNeighborhood(),
                dto.addressCity(),
                dto.addressZipCode(),
                dto.addressState()
        );

        Session session = Session.create(dto.title(), dto.startTime(), event, address);

        Session savedSession = sessionRepository.save(session);
        log.info("Session created with id: {}", savedSession.getId());

        return sessionMapper.toDto(savedSession);
    }

    public List<SessionResponseDto> listAllSessionsFromEventId(UUID eventId) {
        log.info("Listing all sessions for eventId: {}", eventId);
        return sessionRepository.findAllFromEventId(eventId).stream()
                .map(sessionMapper::toDto)
                .toList();
    }
}

