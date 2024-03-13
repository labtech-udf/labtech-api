-- Cria a tabela "eventos.evento_ods".
CREATE TABLE IF NOT EXISTS eventos.evento_ods_tb
(
    evento_id BIGINT NOT NULL REFERENCES eventos.evento_tb,
    ods_id    BIGINT NOT NULL REFERENCES eventos.ods_tb,
    PRIMARY KEY (evento_id, ods_id)
);

ALTER TABLE eventos.evento_ods_tb OWNER TO events;
