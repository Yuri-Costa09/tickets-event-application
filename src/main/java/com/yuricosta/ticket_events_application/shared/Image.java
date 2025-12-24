package com.yuricosta.ticket_events_application.shared;

import jakarta.persistence.Column;

public class Image extends BaseEntity {

    @Column(nullable = false)
    private String url;
}
