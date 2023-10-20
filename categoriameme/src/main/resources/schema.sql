CREATE SEQUENCE sequence_categoria_meme START WITH 1 INCREMENT BY 50;

CREATE TABLE categoria_meme (
    id BIGINT NOT NULL,
    nome VARCHAR(255) NOT NULL,
    descricao VARCHAR(255) NOT NULL,
    data_cadastro date NOT NULL,
    usuario_id BIGINT,
    CONSTRAINT pk_categoriameme PRIMARY KEY (id)
);
