package com.garagem.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class JdbcDemo implements CommandLineRunner {

    private final JdbcTemplate jdbc;

    public JdbcDemo(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public void run(String... args) {
        try {
            System.out.println("--- JDBC Demo: SELECT JOIN veiculo + proprietario ---");
            List<Map<String, Object>> rows = jdbc.queryForList(
                    "SELECT v.placa, p.nome AS proprietario FROM veiculo v JOIN proprietario p ON v.proprietario_id = p.id LIMIT 10"
            );
            System.out.println(rows);

            System.out.println("--- JDBC Demo: chamar função calcular_valor ---");
            // Chama função PostgreSQL definida em sql/views_functions.sql
            Double valor = jdbc.queryForObject("SELECT calcular_valor(now() - interval '2 hour', now())", Double.class);
            System.out.println("Valor calculado (2 horas): " + valor);

            System.out.println("--- JDBC Demo: chamar procedure registrar_pagamento (exemplo) ---");
            try {
                jdbc.execute("CALL registrar_pagamento(1, 'PIX', 10.00)");
                System.out.println("Procedure chamada: registrar_pagamento(1, 'PIX', 10.00)");
            } catch (Exception e) {
                System.out.println("Chamada de procedure falhou (talvez a procedure não exista/nenhum dado de movimentacao): " + e.getMessage());
            }

        } catch (Exception e) {
            System.out.println("Erro no JdbcDemo: " + e.getMessage());
        }
    }
}
