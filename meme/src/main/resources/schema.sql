CREATE SEQUENCE sequence_meme START WITH 1 INCREMENT BY 50;

CREATE TABLE meme (
    id BIGINT NOT NULL,
    nome VARCHAR(255) NOT NULL,
    descricao VARCHAR(255) NOT NULL,
    data_cadastro date NOT NULL,
    url VARCHAR(255) NOT NULL,
    categoria_meme_id BIGINT,
    usuario_id BIGINT,
    CONSTRAINT pk_meme PRIMARY KEY (id)
    );
