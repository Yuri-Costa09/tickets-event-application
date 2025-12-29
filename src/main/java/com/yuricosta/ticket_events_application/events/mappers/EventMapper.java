package com.yuricosta.ticket_events_application.events.mappers;

import com.yuricosta.ticket_events_application.events.dtos.EventResponseDto;
import com.yuricosta.ticket_events_application.events.models.Event;
import com.yuricosta.ticket_events_application.sessions.SessionMapper;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = { SessionMapper.class })
public interface EventMapper {

    EventResponseDto toDto(Event event);
}
