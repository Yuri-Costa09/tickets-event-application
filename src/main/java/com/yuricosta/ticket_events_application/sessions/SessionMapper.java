package com.yuricosta.ticket_events_application.sessions;

import com.yuricosta.ticket_events_application.events.mappers.EventMapper;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface SessionMapper {

    Session toEntity(SessionResponseDto sessionResponseDto);

    @Mapping(target = "event_id", source = "event.id")
    SessionResponseDto toDto(Session session);
}