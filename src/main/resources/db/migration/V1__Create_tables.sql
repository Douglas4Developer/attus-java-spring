CREATE TABLE processo (
    id BIGINT PRIMARY KEY,
    numero_processo VARCHAR(50) NOT NULL UNIQUE,
    data_abertura DATE NOT NULL,
    descricao TEXT NOT NULL,
    status VARCHAR(20) NOT NULL
);

CREATE TABLE parte (
    id BIGINT PRIMARY KEY,
    nome_completo VARCHAR(100) NOT NULL,
    cpf_cnpj VARCHAR(20) NOT NULL,
    tipo VARCHAR(20) NOT NULL,
    contato_email VARCHAR(100),
    contato_telefone VARCHAR(20),
    processo_id INT REFERENCES processo(id) ON DELETE CASCADE
);

CREATE TABLE acao (
    id BIGINT PRIMARY KEY,
    tipo VARCHAR(20) NOT NULL,
    data_registro DATE NOT NULL,
    descricao TEXT NOT NULL,
    processo_id INT REFERENCES processo(id) ON DELETE CASCADE
);
