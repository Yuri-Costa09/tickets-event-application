package com.yuricosta.ticket_events_application.events.models;

import com.yuricosta.ticket_events_application.shared.BaseEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "ticket_batches")
public class TicketBatch extends BaseEntity {

    @Column(nullable = false)
    private String name; // e.g., PISTA VIP, PISTA COMUM, CAMAROTE, etc.

    @Column(nullable = false)
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "session_id")
    private Session sessionId;

    @Column(nullable = false)
    private int availableQuantity;

    @Column(nullable = false)
    private int totalQuantity; // Initial quantity of tickets in the batch.
}
