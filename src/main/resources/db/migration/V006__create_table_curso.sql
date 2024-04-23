
CREATE TABLE IF NOT EXISTS eventos.curso_tb
(
    id          BIGINT       NOT NULL PRIMARY KEY,
    nome         VARCHAR(255),
    sigla        VARCHAR(255),
    descricao    VARCHAR(255)
    );

ALTER TABLE eventos.curso_tb OWNER TO events;


CREATE SEQUENCE IF NOT EXISTS eventos.curso_tb_id_seq
    OWNED BY eventos.curso_tb.id
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 9223372036854775807
    MINVALUE -9223372036854775808
    CYCLE;

-- Aplica a sequencia a tabela curso_tb
ALTER TABLE eventos.curso_tb ALTER COLUMN id SET DEFAULT nextval('eventos.curso_tb_id_seq'::regclass);
