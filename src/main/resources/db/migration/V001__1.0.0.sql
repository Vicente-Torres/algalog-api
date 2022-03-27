CREATE TABLE algalog.client(
    id      SERIAL NOT NULL CONSTRAINT pk_client PRIMARY KEY,
    name    VARCHAR NOT NULL,
    phone   VARCHAR NOT NULL UNIQUE,
    email   VARCHAR NOT NULL UNIQUE
);

CREATE TABLE algalog.address(
    id          SERIAL NOT NULL CONSTRAINT pk_address PRIMARY KEY,
    street      VARCHAR NOT NULL,
    district    VARCHAR NOT NULL,
    number      INTEGER,
    complement  VARCHAR,
    CONSTRAINT uk_address UNIQUE (street,district,number)
);

CREATE TABLE algalog.recipient(
    id          SERIAL NOT NULL CONSTRAINT pk_recipient PRIMARY KEY,
    address_id  INTEGER NOT NULL CONSTRAINT fk_address REFERENCES address,
    name        VARCHAR NOT NULL
);

CREATE TABLE algalog.delivery(
    id              SERIAL NOT NULL CONSTRAINT pk_delivery PRIMARY KEY,
    client_id       INTEGER NOT NULL CONSTRAINT fk_client REFERENCES client,
    recipient_id    INTEGER NOT NULL CONSTRAINT fk_recipient REFERENCES recipient,
    fee             DECIMAL NOT NULL,
    status          VARCHAR NOT NULL,
    request_data    TIMESTAMP NOT NULL,
    finished_data   TIMESTAMP
);