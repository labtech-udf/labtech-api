-- Cria o schema "eventos" se ele não existir.
CREATE SCHEMA IF NOT EXISTS eventos AUTHORIZATION labtech;

-- Cria a tabela "eventos.Arquivo_tb".
CREATE TABLE eventos.Arquivo_tb
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255),
    type VARCHAR(255),
    url  VARCHAR(255),
    size BIGINT,
    key  VARCHAR(255)
);
