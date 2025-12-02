# Sistema de Gerenciamento de Garagem

Projeto Spring Boot com Maven, PostgreSQL via Docker Compose.

**Interface:** Menu interativo no terminal (CLI) com CRUD completo para todas as entidades.

## Como rodar:

### 1. Subir banco com Docker Compose:

```powershell
docker-compose up -d
```

### 2. Buildar a aplicação:

```powershell
mvn clean package -DskipTests
```

### 3. Rodar a aplicação (menu no terminal):

```powershell
java -jar target/garagem.jar
```

A aplicação iniciará com um **menu interativo** no terminal que permite:
- Gerenciar Proprietários (Criar, Listar, Atualizar, Deletar)
- Gerenciar Veículos (Criar, Listar, Atualizar, Deletar)
- Gerenciar Vagas (Criar, Listar, Atualizar, Deletar)
- Gerenciar Movimentações (Criar, Listar, Atualizar, Deletar)


## Estrutura do Projeto

```
src/main/java/com/garagem/
├── config/       (CliRunner - executa menu ao iniciar)
├── controller/   (Controllers REST - opcionais)
├── dto/          (Data Transfer Objects)
├── model/        (Entidades JPA)
├── repository/   (Spring Data JPA Repositories)
└── service/      (Serviços de negócio + MenuService)
```

## Regras de Negócio

- **Movimentação**: Valor calculado automaticamente em R$ 5,00 por hora (arredonda para cima)
- **BD**: DDL automático (Hibernate) ao iniciar
- **Banco**: PostgreSQL 16 via Docker Compose

## Documentos exigidos pelo professor

Os artefatos exigidos pelo relatório foram adicionados a este repositório:

- `docs/RELATORIO_TEXTO.md` - Texto para capa, identificação, descrição do sistema e conclusão.
- `docs/er_diagram.svg` - Diagrama ER (imagem) com 5 entidades.
- `sql/ddl_garagem.sql` - Script DDL (CREATE TABLE) para o banco.
- `sql/dml_exemplos.sql` - Scripts DML de exemplo (INSERT / UPDATE / DELETE / SELECT com JOIN).
- `sql/views_functions.sql` - Exemplo de VIEW, FUNCTION e PROCEDURE (Postgres).
- `src/main/java/com/garagem/config/JdbcDemo.java` - Classe demo que usa `JdbcTemplate` para demonstrar queries e chamadas a function/procedure; útil para capturar prints no console.

Recomendações rápidas para gerar evidências (prints/ddl gerado pelo banco):

1. Subir o banco: `docker-compose up -d`
2. Buildar e rodar a aplicação: `mvn clean package -DskipTests` e `java -jar target/garagem.jar`
3. Capturar os logs do console (o Hibernate exibirá SQL devido a `spring.jpa.show-sql=true`).
4. Para extrair o DDL gerado pelo Postgres: entrar no container e usar `pg_dump -s` 
