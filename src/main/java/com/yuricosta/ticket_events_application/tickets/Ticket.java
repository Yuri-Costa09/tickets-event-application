package com.yuricosta.ticket_events_application.tickets;

import com.yuricosta.ticket_events_application.events.models.Event;
import com.yuricosta.ticket_events_application.events.models.TicketBatch;
import com.yuricosta.ticket_events_application.shared.BaseEntity;
import com.yuricosta.ticket_events_application.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tickets")
@Getter @Setter
public class Ticket extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    // TODO: Implementar logica de geracao de QR Code e imagens
    @Column
    private String qrCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ticket_batch_id", nullable = false)
    private TicketBatch batch; // Saber se é VIP, Pista, etc.
}
