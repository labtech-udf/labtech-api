-- V002__create_tables.sql

-- Cria o schema "eventos" se ele não existir.
CREATE SCHEMA IF NOT EXISTS eventos AUTHORIZATION labtech;

-- Cria a tabela "eventos.Arquivo_tb".
CREATE TABLE eventos.arquivo_tb
(
    id           BIGINT       NOT NULL PRIMARY KEY,
    created_date TIMESTAMP(6) NOT NULL,
    created_by   VARCHAR(255) NOT NULL,
    excluded     BOOLEAN,
    updated_date TIMESTAMP(6) NOT NULL,
    updated_by   VARCHAR(255) NOT NULL,
    key          VARCHAR(255),
    name         VARCHAR(255),
    size         BIGINT,
    type         VARCHAR(255),
    url          VARCHAR(255)
);

ALTER TABLE eventos.arquivo_tb OWNER TO labtech;

-- Cria a tabela "eventos.categoria_evento_tb".
CREATE TABLE eventos.categoria_evento_tb
(
    id           BIGINT       NOT NULL PRIMARY KEY,
    created_date TIMESTAMP(6) NOT NULL,
    created_by   VARCHAR(255) NOT NULL,
    excluded     BOOLEAN,
    updated_date TIMESTAMP(6) NOT NULL,
    updated_by   VARCHAR(255) NOT NULL,
    descricao    VARCHAR(255),
    name         VARCHAR(255)
);

ALTER TABLE eventos.categoria_evento_tb OWNER TO labtech;

-- Cria a tabela "eventos.evento_tb".
CREATE TABLE eventos.evento_tb
(
    id           BIGINT       NOT NULL PRIMARY KEY,
    created_date TIMESTAMP(6) NOT NULL,
    created_by   VARCHAR(255) NOT NULL,
    excluded     BOOLEAN,
    updated_date TIMESTAMP(6) NOT NULL,
    updated_by   VARCHAR(255) NOT NULL,
    address      VARCHAR(255),
    cor          VARCHAR(255),
    date_hora    VARCHAR(255),
    description  TEXT,
    name         VARCHAR(255),
    name_card    VARCHAR(255),
    status       VARCHAR(255)
        CONSTRAINT evento_tb_status_check CHECK (status IN ('C', 'AV', 'R', 'IN', 'F', 'AP')),
    photo_id     BIGINT
        CONSTRAINT fk157hrp81o2g049knufbyat8kw REFERENCES eventos.arquivo_tb
);

ALTER TABLE eventos.evento_tb OWNER TO labtech;

-- Cria a tabela "eventos.ods_tb".
CREATE TABLE eventos.ods_tb
(
    id           BIGINT       NOT NULL PRIMARY KEY,
    created_date TIMESTAMP(6) NOT NULL,
    created_by   VARCHAR(255) NOT NULL,
    excluded     BOOLEAN,
    updated_date TIMESTAMP(6) NOT NULL,
    updated_by   VARCHAR(255) NOT NULL,
    description  VARCHAR(255),
    name         VARCHAR(255),
    status       BOOLEAN      NOT NULL
);

ALTER TABLE eventos.ods_tb OWNER TO labtech;

-- Cria a tabela "eventos.evento_categoria".
CREATE TABLE eventos.evento_categoria
(
    evento_id           BIGINT NOT NULL REFERENCES eventos.evento_tb,
    evento_categoria_id BIGINT NOT NULL REFERENCES eventos.categoria_evento_tb,
    PRIMARY KEY (evento_id, evento_categoria_id)
);

ALTER TABLE eventos.evento_categoria OWNER TO labtech;

-- Cria a tabela "eventos.evento_ods".
CREATE TABLE eventos.evento_ods
(
    evento_id BIGINT NOT NULL REFERENCES eventos.evento_tb,
    ods_id    BIGINT NOT NULL REFERENCES eventos.ods_tb,
    PRIMARY KEY (evento_id, ods_id)
);

ALTER TABLE eventos.evento_ods OWNER TO labtech;
