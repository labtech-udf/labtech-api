-- -- Cria a tabela "eventos.Users_tb".
-- CREATE TABLE IF NOT EXISTS eventos.Users_tb
-- (
--     id             BIGINT       NOT NULL PRIMARY KEY,
--     created_date   TIMESTAMP(6) NOT NULL,
--     created_by     VARCHAR(255) NOT NULL,
--     excluded       BOOLEAN      NOT NULL,
--     updated_date   TIMESTAMP(6) NOT NULL,
--     updated_by     VARCHAR(255) NOT NULL,
--     uid            UUID,
--     name           VARCHAR(255),
--     email          VARCHAR(255),
--     password       VARCHAR(255),
--     foto_perfil_id BIGINT,
--     foto_capa_id   BIGINT,
--     CONSTRAINT fk_foto_perfil FOREIGN KEY (foto_perfil_id) REFERENCES eventos.arquivo_tb (id),
--     CONSTRAINT fk_foto_capa FOREIGN KEY (foto_capa_id) REFERENCES eventos.arquivo_tb (id)
-- );
--
-- -- Cria a tabela para armazenar as permissões dos usuários.
-- CREATE TABLE IF NOT EXISTS eventos.User_Permission_tb
-- (
--     id         BIGINT       NOT NULL PRIMARY KEY,
--     permission VARCHAR(255) NOT NULL
-- );
--
-- -- Cria a tabela de junção para associar as permissões aos usuários.
-- CREATE TABLE IF NOT EXISTS eventos.User_Permission_Assignment_tb
-- (
--     user_id       BIGINT NOT NULL,
--     permission_id BIGINT NOT NULL,
--     PRIMARY KEY (user_id, permission_id),
--     FOREIGN KEY (user_id) REFERENCES eventos.Users_tb (id),
--     FOREIGN KEY (permission_id) REFERENCES eventos.User_Permission_tb (id)
-- );
--
-- -- Cria a sequencia para eventos.Users_tb
-- CREATE SEQUENCE IF NOT EXISTS eventos.Users_tb_id_seq
--     OWNED BY eventos.Users_tb.id
--     START WITH 1
--     INCREMENT BY 1
--     MAXVALUE 9223372036854775807
--     MINVALUE -9223372036854775808
--     CYCLE;
--
-- -- Aplica a sequencia a tabela Users_tb
-- ALTER TABLE eventos.Users_tb
--     ALTER COLUMN id SET DEFAULT nextval('eventos.Users_tb_id_seq'::regclass);


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
