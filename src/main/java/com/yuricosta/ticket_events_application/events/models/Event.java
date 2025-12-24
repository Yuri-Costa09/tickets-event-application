package com.yuricosta.ticket_events_application.events.models;

import com.yuricosta.ticket_events_application.shared.BaseEntity;
import com.yuricosta.ticket_events_application.shared.Image;
import com.yuricosta.ticket_events_application.tickets.Ticket;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "events")
public class Event extends BaseEntity {
    @Column(nullable = false)
    private String title;

    // Utilize native POSTGRES TEXT type for description
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @OneToMany
    private List<Session> sessions;

    @Column(nullable = false)
    private Image banner_url;

    @OneToMany
    private List<Ticket> tickets = new ArrayList<>();
}
