-- SQL opcional para popular tabelas iniciais

INSERT INTO proprietarios (nome, telefone) VALUES ('João Silva', '555-1234');
INSERT INTO proprietarios (nome, telefone) VALUES ('Maria Souza', '555-5678');

INSERT INTO vagas (numero, tipo) VALUES ('1', 'COMUM');
INSERT INTO vagas (numero, tipo) VALUES ('2', 'PCD');

-- Veículos e movimentações podem ser inseridos via API
