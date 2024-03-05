-- Cria a tabela "eventos.ods_tb".
CREATE TABLE IF NOT EXISTS  eventos.ods_tb
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

ALTER TABLE eventos.ods_tb
    OWNER TO labtech;

-- Cria a sequencia para eventos.ods_tb
CREATE SEQUENCE IF NOT EXISTS eventos.ods_tb_id_seq
    OWNED BY eventos.ods_tb.id
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 9223372036854775807
    MINVALUE -9223372036854775808
    CYCLE;

-- Aplica a sequencia a tabela ods_tb
ALTER TABLE eventos.ods_tb
    ALTER COLUMN id SET DEFAULT nextval('eventos.ods_tb_id_seq'::regclass);

ALTER TABLE eventos.ods_tb
    ALTER COLUMN created_date SET DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE eventos.ods_tb
    ALTER COLUMN updated_date SET DEFAULT CURRENT_TIMESTAMP;

