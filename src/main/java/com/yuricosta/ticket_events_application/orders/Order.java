package com.yuricosta.ticket_events_application.orders;

import com.yuricosta.ticket_events_application.events.enums.OrderStatus;
import com.yuricosta.ticket_events_application.shared.BaseEntity;
import com.yuricosta.ticket_events_application.user.User;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Order extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // equals to the
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal total;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status;

    @Column
    private LocalDateTime paidAt;

    @Column
    private LocalDateTime expiresAt;

    @OneToMany
    private List<OrderItem> items = new ArrayList<>();
}
