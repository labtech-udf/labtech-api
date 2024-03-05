-- Cria a tabela "eventos.evento_tb".
CREATE TABLE IF NOT EXISTS eventos.evento_tb
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
    status       VARCHAR(255) CONSTRAINT evento_tb_status_check CHECK (status IN ('C', 'AV', 'R', 'IN', 'F', 'AP')),
    photo_id     BIGINT CONSTRAINT fk157hrp81o2g049knufbyat8kw REFERENCES eventos.arquivo_tb
);

ALTER TABLE eventos.evento_tb OWNER TO labtech;

-- Cria a sequencia para eventos.evento_tb
CREATE SEQUENCE IF NOT EXISTS eventos.evento_tb_id_seq
    OWNED BY eventos.evento_tb.id
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 9223372036854775807
    MINVALUE -9223372036854775808
    CYCLE;

-- Aplica a sequencia a tabela evento_tb
ALTER TABLE eventos.evento_tb ALTER COLUMN id SET DEFAULT nextval('eventos.evento_tb_id_seq'::regclass);
