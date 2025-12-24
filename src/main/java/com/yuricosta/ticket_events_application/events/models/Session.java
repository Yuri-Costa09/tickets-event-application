package com.yuricosta.ticket_events_application.events.models;

import com.yuricosta.ticket_events_application.events.valueObjects.Address;
import com.yuricosta.ticket_events_application.shared.BaseEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sessions")
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