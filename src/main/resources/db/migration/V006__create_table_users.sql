-- Migração para criar a tabela Users_tb
CREATE TABLE eventos.Users_tb
(
    id             SERIAL PRIMARY KEY,
    uid            UUID      DEFAULT gen_random_uuid() NOT NULL,
    name           VARCHAR(255)                        NOT NULL,
    email          VARCHAR(255)                        NOT NULL,
    password       VARCHAR(255)                        NOT NULL,
    foto_perfil_id BIGINT,
    foto_capa_id   BIGINT,
    excluded       BOOLEAN   DEFAULT FALSE,
    created_date   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by     VARCHAR(255),
    updated_date   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by     VARCHAR(255),
    CONSTRAINT fk_foto_perfil FOREIGN KEY (foto_perfil_id) REFERENCES eventos.arquivo_tb (id),
    CONSTRAINT fk_foto_capa FOREIGN KEY (foto_capa_id) REFERENCES eventos.arquivo_tb (id)
);

-- Migração para criar a tabela auxiliar user_permissions
CREATE TABLE eventos.user_permissions
(
    user_id BIGINT       NOT NULL,
    roles   VARCHAR(255) NOT NULL,
    CONSTRAINT user_permissions_pk PRIMARY KEY (user_id, roles),
    CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES eventos.Users_tb (id)
);
