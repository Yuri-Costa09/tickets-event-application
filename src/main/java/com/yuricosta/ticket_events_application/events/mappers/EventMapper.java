package com.yuricosta.ticket_events_application.events.mappers;

import com.yuricosta.ticket_events_application.events.dtos.EventResponseDto;
import com.yuricosta.ticket_events_application.events.models.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EventMapper {
    EventResponseDto toDto(Event event);
}
