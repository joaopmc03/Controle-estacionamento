-- Dados iniciais para demonstração
INSERT INTO proprietario (id, nome, telefone) VALUES (1, 'Maria Silva', '11999990000');
INSERT INTO proprietario (id, nome, telefone) VALUES (2, 'Carlos Souza', '21988880000');

INSERT INTO vaga (id, numero, tipo) VALUES (1, 'A1', 'COMUM');
INSERT INTO vaga (id, numero, tipo) VALUES (2, 'P1', 'PCD');

-- Obs: dependências de FK com veiculo/movimentacao podem exigir ordem de inserção
