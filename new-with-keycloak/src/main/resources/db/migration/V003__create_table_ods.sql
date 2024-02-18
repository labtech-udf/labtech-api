-- Cria a tabela "eventos.ods_tb".
CREATE TABLE eventos.ods_tb
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
CREATE SEQUENCE eventos.ods_tb_id_seq
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


INSERT INTO eventos.ods_tb (id, created_by, updated_by, excluded, name, description, status)
VALUES (1, 'admin', 'admin', FALSE, 'Erradicação da Pobreza',
        'Acabar com a pobreza em todas as suas formas, em todos os lugares', TRUE),
       (2, 'admin', 'admin', FALSE, 'Fome Zero e Agricultura Sustentável',
        'Acabar com a fome, alcançar a segurança alimentar e nutrição melhorada e promover a agricultura sustentável',
        TRUE),
       (3, 'admin', 'admin', FALSE, 'Saúde de Qualidade',
        'Assegurar uma vida saudável e promover o bem-estar para todos, em todas as idades', TRUE),
       (4, 'admin', 'admin', FALSE, 'Educação de Qualidade',
        'Assegurar a educação inclusiva e de qualidade para todos e promover oportunidades de aprendizagem ao longo da vida',
        TRUE),
       (5, 'admin', 'admin', FALSE, 'Igualdade de Gênero',
        'Alcançar a igualdade de gênero e empoderar todas as mulheres e meninas', TRUE),
       (6, 'admin', 'admin', FALSE, 'Água Potável e Saneamento',
        'Assegurar a disponibilidade e gestão sustentável da água e do saneamento para todos', TRUE),
       (7, 'admin', 'admin', FALSE, 'Energia Acessível e Limpa',
        'Assegurar o acesso à energia acessível, confiável, sustentável e moderna para todos', TRUE),
       (8, 'admin', 'admin', FALSE, 'Trabalho Decente e Crescimento Econômico',
        'Promover o crescimento econômico inclusivo e sustentável, o emprego pleno e produtivo e o trabalho decente para todos',
        TRUE),
       (9, 'admin', 'admin', FALSE, 'Indústria, Inovação e Infraestrutura',
        'Construir uma infraestrutura resiliente, promover a industrialização inclusiva e sustentável e fomentar a inovação',
        TRUE),
       (10, 'admin', 'admin', FALSE, 'Redução das Desigualdades',
        'Reduzir a desigualdade dentro dos países e entre eles', TRUE),
       (11, 'admin', 'admin', FALSE, 'Cidades e Comunidades Sustentáveis',
        'Tornar as cidades e os assentamentos humanos inclusivos, seguros, resilientes e sustentáveis', TRUE),
       (12, 'admin', 'admin', FALSE, 'Produção e Consumo Responsáveis',
        'Assegurar padrões de produção e consumo sustentáveis', TRUE),
       (13, 'admin', 'admin', FALSE, 'Ação contra a Mudança Global do Clima',
        'Tomar medidas urgentes para combater a mudança climática e seus impactos', TRUE),
       (14, 'admin', 'admin', FALSE, 'Vida na Água',
        'Conservar e usar de forma sustentável os oceanos, mares e recursos marinhos para o desenvolvimento sustentável',
        TRUE),
       (15, 'admin', 'admin', FALSE, 'Vida Terrestre',
        'Proteger, restaurar e promover o uso sustentável dos ecossistemas terrestres, gerir de forma sustentável as florestas, combater a desertificação e deter e reverter a degradação da terra e a perda de biodiversidade',
        TRUE),
       (16, 'admin', 'admin', FALSE, 'Paz, Justiça e Instituições Fortes',
        'Promover sociedades pacíficas e inclusivas para o desenvolvimento sustentável, proporcionar o acesso à justiça para todos e construir instituições eficazes, responsáveis e inclusivas em todos os níveis',
        TRUE),
       (17, 'admin', 'admin', FALSE, 'Parcerias para as Metas',
        'Revigorar a parceria global para o desenvolvimento sustentável e fortalecer os meios de implementação e revitalizar a Parceria Global para o Desenvolvimento Sustentável',
        TRUE);
