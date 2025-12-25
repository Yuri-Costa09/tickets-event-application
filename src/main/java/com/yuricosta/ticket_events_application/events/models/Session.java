package com.yuricosta.ticket_events_application.events.models;

import com.yuricosta.ticket_events_application.events.valueObjects.Address;
import com.yuricosta.ticket_events_application.shared.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// TODO: Add something like "isActive" field to disable filled or inactive sessions.
@Entity
@Table(name = "sessions")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Session extends BaseEntity {

    @Column
    private String title; // Will be the name of the event + number (e.g. "Dream theater #1, #2..")

    @Column
    private LocalDateTime startTime;

    @OneToMany
    private List<TicketBatch> ticketBatches = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @Embedded
    private Address address;
}