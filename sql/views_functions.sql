-- Views, Functions and Procedures de exemplo (PostgreSQL)

-- VIEW que combina veículo e proprietário
CREATE OR REPLACE VIEW vw_veiculo_proprietario AS
SELECT v.id AS veiculo_id, v.placa, v.modelo, v.cor, p.id AS proprietario_id, p.nome AS proprietario_nome
FROM veiculo v
JOIN proprietario p ON v.proprietario_id = p.id;

-- Função que calcula valor com base em horas (R$5 por hora, arredondando para cima)
CREATE OR REPLACE FUNCTION calcular_valor(entrada timestamptz, saida timestamptz)
RETURNS numeric AS $$
BEGIN
  IF saida IS NULL THEN
    RETURN 0;
  END IF;
  RETURN ceil(EXTRACT(epoch FROM (saida - entrada)) / 3600) * 5; -- R$5/h arredondando p/ cima
END;
$$ LANGUAGE plpgsql;

-- Procedure de exemplo que registra pagamento para uma movimentação
CREATE OR REPLACE PROCEDURE registrar_pagamento(mov_id INTEGER, metodo VARCHAR, valor NUMERIC)
LANGUAGE plpgsql
AS $$
BEGIN
  INSERT INTO pagamento (movimentacao_id, metodo, valor_pago) VALUES (mov_id, metodo, valor);
  UPDATE movimentacao SET pagamento_id = (SELECT id FROM pagamento WHERE movimentacao_id = mov_id LIMIT 1) WHERE id = mov_id;
END;
$$;

-- Observação: Para chamar a procedure no PostgreSQL use: CALL registrar_pagamento(1, 'PIX', 10.00);
