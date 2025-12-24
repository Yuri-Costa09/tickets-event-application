package com.yuricosta.ticket_events_application.events.valueObjects;

import jakarta.persistence.Embeddable;

@Embeddable
public class Address {
    private String street;
    private String number;
    private String neighborhood;
    private String city;
    private String zipCode;
    private String state;
}
