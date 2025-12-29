package com.yuricosta.ticket_events_application.sessions;

import com.yuricosta.ticket_events_application.events.models.Event;
import com.yuricosta.ticket_events_application.events.models.TicketBatch;
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

    @OneToMany(mappedBy = "session", fetch = FetchType.LAZY)
    private List<TicketBatch> ticketBatches = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "event_id")
    private Event event;

    @Embedded
    private Address address;

    static public Session create(String title, LocalDateTime startTime, Event event, Address address) {
        Session session = new Session();
        session.setTitle(title);
        session.setStartTime(startTime);
        session.setEvent(event);
        session.setAddress(address);
        return session;
    }
}