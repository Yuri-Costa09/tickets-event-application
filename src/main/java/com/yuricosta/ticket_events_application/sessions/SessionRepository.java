package com.yuricosta.ticket_events_application.sessions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SessionRepository extends JpaRepository<Session, UUID> {
    @Query("select s from Session s where s.event.id = :eventId")
    List<Session> findAllFromEventId(@Param("eventId") UUID eventId);
}