-- DDL para o sistema Gerenciamento de Garagens (PostgreSQL)

CREATE TABLE proprietario (
  id SERIAL PRIMARY KEY,
  nome VARCHAR(255) NOT NULL,
  telefone VARCHAR(50)
);

CREATE TABLE veiculo (
  id SERIAL PRIMARY KEY,
  placa VARCHAR(20) NOT NULL UNIQUE,
  modelo VARCHAR(255),
  cor VARCHAR(100),
  proprietario_id INTEGER NOT NULL,
  CONSTRAINT fk_veiculo_proprietario FOREIGN KEY (proprietario_id) REFERENCES proprietario(id) ON DELETE CASCADE
);

CREATE TABLE vaga (
  id SERIAL PRIMARY KEY,
  numero VARCHAR(50) NOT NULL UNIQUE,
  tipo VARCHAR(100)
);

CREATE TABLE movimentacao (
  id SERIAL PRIMARY KEY,
  veiculo_id INTEGER NOT NULL,
  vaga_id INTEGER NOT NULL,
  entrada TIMESTAMPTZ NOT NULL DEFAULT now(),
  saida TIMESTAMPTZ,
  valor NUMERIC(10,2),
  pagamento_id INTEGER,
  CONSTRAINT fk_mov_veiculo FOREIGN KEY (veiculo_id) REFERENCES veiculo(id) ON DELETE CASCADE,
  CONSTRAINT fk_mov_vaga FOREIGN KEY (vaga_id) REFERENCES vaga(id) ON DELETE SET NULL
);

-- Nova entidade: pagamento (adicionada para atingir o mínimo de 5 entidades)
CREATE TABLE pagamento (
  id SERIAL PRIMARY KEY,
  movimentacao_id INTEGER UNIQUE,
  metodo VARCHAR(100),
  valor_pago NUMERIC(10,2) NOT NULL,
  pago_em TIMESTAMPTZ DEFAULT now(),
  CONSTRAINT fk_pag_mov FOREIGN KEY (movimentacao_id) REFERENCES movimentacao(id) ON DELETE CASCADE
);

-- Observação: a FK fk_mov_pagamento referencia pagamento(id) acima; para evitar ciclo na criação manual,
-- recomenda-se criar pagamento após movimentacao (ou usar scripts que criem tabelas sem FKs e depois adicionem FKs).

-- Índices adicionais
CREATE INDEX idx_veiculo_proprietario ON veiculo(proprietario_id);
CREATE INDEX idx_mov_veiculo ON movimentacao(veiculo_id);
