package com.yuricosta.ticket_events_application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class TicketEventsApplication {

	public static void main(String[] args) {
		SpringApplication.run(TicketEventsApplication.class, args);
	}

}
