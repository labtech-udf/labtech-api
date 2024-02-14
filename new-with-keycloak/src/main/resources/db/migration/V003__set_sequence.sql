-- Cria a sequência geral no schema "eventos"
CREATE SEQUENCE eventos.sequencia_id START WITH 1 INCREMENT BY 1 MAXVALUE 9223372036854775807 MINVALUE -9223372036854775808 CYCLE;

-- Aplica a sequência às tabelas no schema "eventos"

-- Tabela arquivo_tb
ALTER TABLE eventos.arquivo_tb ALTER COLUMN id SET DEFAULT nextval('eventos.sequencia_id'::regclass);

-- Tabela categoria_evento_tb
ALTER TABLE eventos.categoria_evento_tb ALTER COLUMN id SET DEFAULT nextval('eventos.sequencia_id'::regclass);

-- Tabela evento_tb
ALTER TABLE eventos.evento_tb ALTER COLUMN id SET DEFAULT nextval('eventos.sequencia_id'::regclass);

-- Tabela ods_tb
ALTER TABLE eventos.ods_tb ALTER COLUMN id SET DEFAULT nextval('eventos.sequencia_id'::regclass);

-- Tabela evento_categoria
ALTER TABLE eventos.evento_categoria ALTER COLUMN evento_id SET DEFAULT nextval('eventos.sequencia_id'::regclass);
ALTER TABLE eventos.evento_categoria ALTER COLUMN evento_categoria_id SET DEFAULT nextval('eventos.sequencia_id'::regclass);

-- Tabela evento_ods
ALTER TABLE eventos.evento_ods ALTER COLUMN evento_id SET DEFAULT nextval('eventos.sequencia_id'::regclass);
ALTER TABLE eventos.evento_ods ALTER COLUMN ods_id SET DEFAULT nextval('eventos.sequencia_id'::regclass);
