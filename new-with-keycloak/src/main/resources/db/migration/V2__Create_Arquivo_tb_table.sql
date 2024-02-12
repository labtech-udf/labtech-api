CREATE TABLE eventos.Arquivo_tb
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255),
    type VARCHAR(255),
    url  VARCHAR(255),
    size BIGINT,
    key  VARCHAR(255)
);
