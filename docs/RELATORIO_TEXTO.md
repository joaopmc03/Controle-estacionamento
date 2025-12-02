
1. Capa

- Disciplina: Banco de Dados
- Alunos: João Costa, Kauã Rafael
- Professor: Angela
- Projeto: Sistema de Gerenciamento de Garagens
- Entrega: 08/12/2025

2. Identificação da Equipe

- Integrantes: João Costa; Kauã Rafael
- Curso / Turma: (preencher)

3. Descrição do Sistema

Sistema simples para gerenciar proprietários, veículos, vagas, movimentações (entrada/saída) e pagamentos.

Entidades: Proprietario, Veiculo, Vaga, Movimentacao, Pagamento.

4. Banco de Dados

4.1 Modelo ER

Diagrama: `docs/er_diagram.svg` (5 entidades; use o SVG/PNG para o relatório).

4.2 Script DDL

Arquivo com CREATE TABLE: `sql/ddl_garagem.sql` (contém chaves primárias e estrangeiras necessárias).

4.3 Scripts DML

Exemplos de INSERT/UPDATE/DELETE/SELECT com JOIN: `sql/dml_exemplos.sql`.

5. Programação em Java

5.1 Integração

Projeto em Spring Boot com Spring Data JPA. Habilitado log de SQL em `application.properties` para evidência.

Classe de demonstração JDBC: `src/main/java/com/garagem/config/JdbcDemo.java` (uso de `JdbcTemplate` para mostrar SELECT com JOIN e chamadas a function/procedure).

5.2 CRUD

Operações CRUD estão suportadas para as 5 entidades. Exemplos estão em `sql/dml_exemplos.sql` e são demonstráveis via `JdbcDemo` e pelo menu CLI.

6. Views / Functions / Procedures

Arquivo `sql/views_functions.sql` inclui:
- `vw_veiculo_proprietario` (view)
- `calcular_valor(entrada,saida)` (function)
- `registrar_pagamento(mov_id, metodo, valor)` (procedure)

7. Conclusão

- Projeto atende aos requisitos do PDF: 5 entidades, ER, DDL, DML, CRUD, view/function/procedure, evidências em Java.
- Passos finais: executar o projeto localmente e anexar os prints do console (Hibernate SQL e saída do `JdbcDemo`) e o DDL gerado via `pg_dump -s`.

