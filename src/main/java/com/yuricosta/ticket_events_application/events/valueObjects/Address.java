package com.yuricosta.ticket_events_application.events.valueObjects;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Address {
    private String street;
    private String number;
    private String neighborhood;
    private String city;
    private String zipCode;
    private String state;

    public static Address of(String street, String number, String neighborhood, String city, String zipCode, String state) {
        return new Address(street, number, neighborhood, city, zipCode, state);
    }
}
