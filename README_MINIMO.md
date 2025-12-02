# Sistema de Gerenciamento de Garagem (mínimo)


Principais pontos:
- Interface: menu interativo no terminal.
- Banco: PostgreSQL (configurado via `docker-compose.yml`).

Requisitos mínimos no computador onde vai executar:
- Java 21 JDK instalado (`java` no PATH).
- Docker + Docker Compose (recomendado) para subir o PostgreSQL OR PostgreSQL instalado localmente.
- Maven para construir o projeto (ou use o Maven Wrapper se disponível).

Passos rápidos (PowerShell):

1) Subir o banco com Docker Compose (recomendado):

```powershell
# Sistema de Gerenciamento de Garagem (mínimo)

Projeto mínimo para entrega: aplicação CLI (menu no terminal) que realiza CRUD das entidades e persiste em PostgreSQL.

Principais pontos:
- Interface: menu interativo no terminal (não expõe API REST por padrão).
- Banco: PostgreSQL (configurado via `docker-compose.yml`).

Requisitos mínimos no computador onde vai executar:
- Java 21 JDK instalado (`java` no PATH).
- Docker + Docker Compose (recomendado) para subir o PostgreSQL OU PostgreSQL instalado localmente.
- Maven para construir o projeto (ou use o Maven Wrapper se disponível).

Passos rápidos (PowerShell):

1) Subir o banco com Docker Compose (recomendado):

```powershell
docker compose up -d
```

2) Buildar a aplicação:

```powershell
mvn clean package -DskipTests
```

3) Rodar a aplicação (menu CLI):

```powershell
java -jar target/garagem-0.0.1-SNAPSHOT.jar
```

4) Parar o banco:

```powershell
docker compose down
```

Se você não tiver Docker:
- Instale PostgreSQL localmente e atualize `spring.datasource.url` em `src/main/resources/application.properties` para `jdbc:postgresql://localhost:5432/garagem_db` (ou o host/porta que usar).
- Outra opção de teste rápido é alterar a configuração para usar H2 (memória), mas isso altera o comportamento em relação ao PostgreSQL.

Observações úteis:
- O jar gerado fica em `target/` após o `mvn package`.
- A aplicação abre um menu no terminal para gerenciar `Proprietario`, `Veiculo`, `Vaga` e `Movimentacao`.

Arquivo ZIP gerado automaticamente: `garagem-project.zip` (na raiz do projeto) — contém todo o código pronto para envio.

Nota para apresentação:
- Durante a demonstração, abra `src/main/java/com/garagem/service/MenuService.java` para mostrar onde o fluxo do menu está implementado.
- A função que calcula o valor está em `src/main/java/com/garagem/service/MovimentacaoService.java` (método `calcularValor`).

Maven Wrapper (ajuda a quem não tem Maven):
- Adicionei dois scripts simples na raiz: `mvnw` (Linux/macOS) e `mvnw.cmd` (Windows). Eles tentam usar o `mvn` se já estiver instalado.
- Se você não tiver `mvn`, há um script `mvnw-bootstrap.ps1` que tenta baixar o `maven-wrapper.jar` automaticamente (requer internet). Após o bootstrap, execute `.
mvnw.cmd clean package -DskipTests`.

Boa apresentação! :rocket
