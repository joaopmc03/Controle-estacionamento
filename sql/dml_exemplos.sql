-- Scripts DML de exemplo para popular e manipular os dados

-- INSERTs
INSERT INTO proprietario (nome, telefone) VALUES ('João Silva', '99999-0001');
INSERT INTO proprietario (nome, telefone) VALUES ('Maria Oliveira', '99999-0002');

INSERT INTO veiculo (placa, modelo, cor, proprietario_id) VALUES ('ABC1D23', 'Fiat Uno', 'Prata', 1);
INSERT INTO veiculo (placa, modelo, cor, proprietario_id) VALUES ('XYZ9Z99', 'VW Golf', 'Preto', 2);

INSERT INTO vaga (numero, tipo) VALUES ('A01', 'Compacto');
INSERT INTO vaga (numero, tipo) VALUES ('B02', 'Grande');

-- Criar movimentação (entrada)
INSERT INTO movimentacao (veiculo_id, vaga_id, entrada) VALUES (1, 1, now());

-- Atualizar movimentação (registro de saída e cálculo de valor manual)
UPDATE movimentacao SET saida = now() + interval '2 hour', valor = 10.00 WHERE id = 1;

-- Exemplo de criação de pagamento e ligação
INSERT INTO pagamento (movimentacao_id, metodo, valor_pago) VALUES (1, 'PIX', 10.00);
-- Atualiza coluna informativa pagamento_id em movimentacao
UPDATE movimentacao SET pagamento_id = 1 WHERE id = 1;

-- SELECT com JOIN (exemplo para relatório)
SELECT m.id, v.placa, p.nome AS proprietario, m.entrada, m.saida, m.valor
FROM movimentacao m
JOIN veiculo v ON m.veiculo_id = v.id
JOIN proprietario p ON v.proprietario_id = p.id;

-- DELETE exemplo
DELETE FROM pagamento WHERE id = 999; -- exemplo de delete sem efeito

-- Exemplo de UPDATE em proprietário
UPDATE proprietario SET telefone = '98888-7777' WHERE id = 2;
