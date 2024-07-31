DROP DATABASE IF EXISTS real_estate;
CREATE DATABASE real_estate ;

\c real_estate;

-- Database

DROP TABLE IF EXISTS real_estate CASCADE;
DROP TABLE IF EXISTS agent;

DROP TYPE IF EXISTS type;
DROP TYPE IF EXISTS status;

CREATE TYPE type AS ENUM ('RESIDENTIAL', 'COMMERCIAL', 'LAND');
CREATE TYPE status AS ENUM ('SALE', 'RENT', 'SOLD');


CREATE TABLE agent
(
    id        BIGINT NOT NULL,
    firstname VARCHAR,
    surname   VARCHAR,
    email     VARCHAR,
    tel       VARCHAR,
    PRIMARY KEY (id)
);

CREATE TABLE real_estate
(
    id            BIGINT NOT NULL,
    type          type,
    status        status,
    price         DOUBLE PRECISION,
    agent_idfk    BIGINT,
    address       VARCHAR,
    size_in_mm    BIGINT,
    num_of_rooms  INT,
    num_of_floors INT,
    has_garage    BOOLEAN,
    built_date    DATE DEFAULT CURRENT_TIMESTAMP,

    PRIMARY KEY (id),
    FOREIGN KEY (agent_idfk) REFERENCES agent (id)
);

