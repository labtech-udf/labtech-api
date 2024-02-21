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

ALTER TABLE eventos.arquivo_tb
    OWNER TO labtech;

-- Cria a sequencia para eventos.arquivo_tb
CREATE SEQUENCE eventos.arquivo_tb_id_seq
    OWNED BY eventos.arquivo_tb.id
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 9223372036854775807
    MINVALUE -9223372036854775808
    CYCLE;

-- Aplica a sequencia a tabela arquivo_tb
ALTER TABLE eventos.arquivo_tb
    ALTER COLUMN id SET DEFAULT nextval('eventos.arquivo_tb_id_seq'::regclass);