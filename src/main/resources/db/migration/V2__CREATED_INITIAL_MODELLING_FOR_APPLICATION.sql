CREATE TABLE events
(
    id          UUID                        NOT NULL,
    created_at  TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at  TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    title       VARCHAR(255)                NOT NULL,
    description TEXT                        NOT NULL,
    image_url   VARCHAR(255)                NOT NULL,
    CONSTRAINT pk_events PRIMARY KEY (id)
);

CREATE TABLE events_sessions
(
    event_id    UUID NOT NULL,
    sessions_id UUID NOT NULL
);

CREATE TABLE events_tickets
(
    event_id   UUID NOT NULL,
    tickets_id UUID NOT NULL
);

CREATE TABLE order_items
(
    id              UUID                        NOT NULL,
    created_at      TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at      TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    ticket_batch_id UUID,
    quantity        INTEGER                     NOT NULL,
    price           DECIMAL(10, 2)              NOT NULL,
    order_id        UUID,
    CONSTRAINT pk_order_items PRIMARY KEY (id)
);

CREATE TABLE orders
(
    id         UUID                        NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    user_id    UUID,
    total      DECIMAL(10, 2)              NOT NULL,
    status     VARCHAR(255)                NOT NULL,
    paid_at    TIMESTAMP WITHOUT TIME ZONE,
    expires_at TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_orders PRIMARY KEY (id)
);

CREATE TABLE orders_items
(
    order_id UUID NOT NULL,
    items_id UUID NOT NULL
);

CREATE TABLE sessions
(
    id           UUID                        NOT NULL,
    created_at   TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at   TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    title        VARCHAR(255),
    start_time   TIMESTAMP WITHOUT TIME ZONE,
    event_id     UUID,
    street       VARCHAR(255),
    number       VARCHAR(255),
    neighborhood VARCHAR(255),
    city         VARCHAR(255),
    zip_code     VARCHAR(255),
    state        VARCHAR(255),
    CONSTRAINT pk_sessions PRIMARY KEY (id)
);

CREATE TABLE sessions_ticket_batches
(
    session_id        UUID NOT NULL,
    ticket_batches_id UUID NOT NULL
);

CREATE TABLE ticket_batches
(
    id                 UUID                        NOT NULL,
    created_at         TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at         TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    name               VARCHAR(255)                NOT NULL,
    price              DECIMAL                     NOT NULL,
    session_id         UUID,
    available_quantity INTEGER                     NOT NULL,
    total_quantity     INTEGER                     NOT NULL,
    CONSTRAINT pk_ticket_batches PRIMARY KEY (id)
);

CREATE TABLE tickets
(
    id              UUID                        NOT NULL,
    created_at      TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at      TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    user_id         UUID,
    qr_code         VARCHAR(255),
    event_id        UUID,
    ticket_batch_id UUID                        NOT NULL,
    CONSTRAINT pk_tickets PRIMARY KEY (id)
);

CREATE TABLE users_orders
(
    user_id   UUID NOT NULL,
    orders_id UUID NOT NULL
);

CREATE TABLE users_tickets
(
    user_id    UUID NOT NULL,
    tickets_id UUID NOT NULL
);

ALTER TABLE events_sessions
    ADD CONSTRAINT uc_events_sessions_sessions UNIQUE (sessions_id);

ALTER TABLE events_tickets
    ADD CONSTRAINT uc_events_tickets_tickets UNIQUE (tickets_id);

ALTER TABLE orders_items
    ADD CONSTRAINT uc_orders_items_items UNIQUE (items_id);

ALTER TABLE sessions_ticket_batches
    ADD CONSTRAINT uc_sessions_ticket_batches_ticketbatches UNIQUE (ticket_batches_id);

ALTER TABLE users_orders
    ADD CONSTRAINT uc_users_orders_orders UNIQUE (orders_id);

ALTER TABLE users_tickets
    ADD CONSTRAINT uc_users_tickets_tickets UNIQUE (tickets_id);

ALTER TABLE orders
    ADD CONSTRAINT FK_ORDERS_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE order_items
    ADD CONSTRAINT FK_ORDER_ITEMS_ON_ORDER FOREIGN KEY (order_id) REFERENCES orders (id);

ALTER TABLE order_items
    ADD CONSTRAINT FK_ORDER_ITEMS_ON_TICKET_BATCH FOREIGN KEY (ticket_batch_id) REFERENCES ticket_batches (id);

ALTER TABLE sessions
    ADD CONSTRAINT FK_SESSIONS_ON_EVENT FOREIGN KEY (event_id) REFERENCES events (id);

ALTER TABLE tickets
    ADD CONSTRAINT FK_TICKETS_ON_EVENT FOREIGN KEY (event_id) REFERENCES events (id);

ALTER TABLE tickets
    ADD CONSTRAINT FK_TICKETS_ON_TICKET_BATCH FOREIGN KEY (ticket_batch_id) REFERENCES ticket_batches (id);

ALTER TABLE tickets
    ADD CONSTRAINT FK_TICKETS_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE ticket_batches
    ADD CONSTRAINT FK_TICKET_BATCHES_ON_SESSION FOREIGN KEY (session_id) REFERENCES sessions (id);

ALTER TABLE events_sessions
    ADD CONSTRAINT fk_eveses_on_event FOREIGN KEY (event_id) REFERENCES events (id);

ALTER TABLE events_sessions
    ADD CONSTRAINT fk_eveses_on_session FOREIGN KEY (sessions_id) REFERENCES sessions (id);

ALTER TABLE events_tickets
    ADD CONSTRAINT fk_evetic_on_event FOREIGN KEY (event_id) REFERENCES events (id);

ALTER TABLE events_tickets
    ADD CONSTRAINT fk_evetic_on_ticket FOREIGN KEY (tickets_id) REFERENCES tickets (id);

ALTER TABLE orders_items
    ADD CONSTRAINT fk_ordite_on_order FOREIGN KEY (order_id) REFERENCES orders (id);

ALTER TABLE orders_items
    ADD CONSTRAINT fk_ordite_on_order_item FOREIGN KEY (items_id) REFERENCES order_items (id);

ALTER TABLE sessions_ticket_batches
    ADD CONSTRAINT fk_sesticbat_on_session FOREIGN KEY (session_id) REFERENCES sessions (id);

ALTER TABLE sessions_ticket_batches
    ADD CONSTRAINT fk_sesticbat_on_ticket_batch FOREIGN KEY (ticket_batches_id) REFERENCES ticket_batches (id);

ALTER TABLE users_orders
    ADD CONSTRAINT fk_useord_on_order FOREIGN KEY (orders_id) REFERENCES orders (id);

ALTER TABLE users_orders
    ADD CONSTRAINT fk_useord_on_user FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE users_tickets
    ADD CONSTRAINT fk_usetic_on_ticket FOREIGN KEY (tickets_id) REFERENCES tickets (id);

ALTER TABLE users_tickets
    ADD CONSTRAINT fk_usetic_on_user FOREIGN KEY (user_id) REFERENCES users (id);