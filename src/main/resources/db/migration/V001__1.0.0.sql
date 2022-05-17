CREATE TABLE algalog.client(
    id      SERIAL NOT NULL CONSTRAINT pk_client PRIMARY KEY,
    name    VARCHAR NOT NULL,
    phone   VARCHAR NOT NULL UNIQUE,
    email   VARCHAR NOT NULL UNIQUE
);

CREATE TABLE algalog.delivery(
    id                              SERIAL NOT NULL CONSTRAINT pk_delivery PRIMARY KEY,
    client_id                       INTEGER NOT NULL CONSTRAINT fk_client REFERENCES client,
    fee                             DECIMAL NOT NULL,
    status                          VARCHAR NOT NULL,
    request_date                    TIMESTAMP NOT NULL,
    finished_date                   TIMESTAMP,

    recipient_name                  VARCHAR NOT NULL,
    recipient_street                VARCHAR NOT NULL,
    recipient_district              VARCHAR NOT NULL,
    recipient_house_number          VARCHAR,
    recipient_address_complement    VARCHAR
);