package com.yuricosta.ticket_events_application.orders;

import com.yuricosta.ticket_events_application.events.models.TicketBatch;
import com.yuricosta.ticket_events_application.shared.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "order_items")
@Getter @Setter
public class OrderItem extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "ticket_batch_id")
    private TicketBatch ticketBatch;

    @Column(nullable = false)
    private int quantity;

    // equals to quantity * ticketBatch.price
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

}
