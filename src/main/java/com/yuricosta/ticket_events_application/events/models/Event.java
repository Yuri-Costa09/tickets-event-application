package com.yuricosta.ticket_events_application.events.models;

import com.yuricosta.ticket_events_application.sessions.Session;
import com.yuricosta.ticket_events_application.shared.BaseEntity;
import com.yuricosta.ticket_events_application.tickets.Ticket;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "events")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Event extends BaseEntity {
    @Column(nullable = false)
    private String title;

    // Utilizing native POSTGRES TEXT type for description
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "event", fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Session> sessions = new ArrayList<>();

    @Column(nullable = false)
    private String imageUrl;

    @OneToMany(mappedBy = "event", fetch = FetchType.EAGER)
    private List<Ticket> tickets = new ArrayList<>();
}
