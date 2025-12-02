# Roteiro de Apresentação

Objetivo: apresentar o projeto compartilhando a tela, executar o sistema no console (menu CLI) e responder perguntas sobre onde o código está localizado.

1) Início (30s)
- Mostrar a raiz do projeto no VS Code.
- Abrir `src/main/java/com/garagem/GaragemApplication.java` (ponto de entrada).
- Abrir `src/main/resources/application.properties` (configuração do BD).

2) Rodar a aplicação (1-2 min)
- No terminal PowerShell, executar:

```powershell
# subir o banco (Docker)
docker compose up -d

# build do projeto (com Maven)
mvn clean package -DskipTests

# executar o jar
java -jar target/garagem-0.0.1-SNAPSHOT.jar
```

- Se não tiver Maven, peça ao professor para permitir usar os comandos ou use `mvnw.cmd` se disponível.

3) Demonstração no console (3-5 min)
- Mostrar o menu que aparece no console.
- Executar a sequência: criar Proprietário → criar Veículo → criar Vaga → criar Movimentação.
- Mostrar o cálculo do valor após criar a movimentação (linha que imprime `Valor calculado`).

4) Navegar no código (2-3 min)
- Mostrar `src/main/java/com/garagem/service/MenuService.java` — aqui está a interface de usuário (prompts e fluxo).
- Mostrar `src/main/java/com/garagem/service/MovimentacaoService.java` — aqui está a regra `calcularValor(...)`.
- Mostrar `src/main/java/com/garagem/repository/` — repositórios `JpaRepository` usados para persistência.

5) Perguntas típicas e respostas curtas
- "Onde está a função que calcula o valor?"
  - Resposta: `MovimentacaoService.calcularValor(...)` — abre o arquivo e explique a lógica de arredondar para cima e multiplicar por 5.
- "Onde se cria um proprietário?"
  - Resposta: `MenuService.criarProprietario()` chama `ProprietarioService.create(...)`.
- "Como os dados são salvos no banco?"
  - Resposta: Via `ProprietarioRepository`, `VeiculoRepository`, `VagaRepository` e `MovimentacaoRepository` (interfaces em `src/main/java/com/garagem/repository`).

6) Nota sobre autoria/humanização
- Durante a apresentação, diga que recebeu ajuda para criar a estrutura, mas que implementou e testou as partes principais (mostre os trechos comentados com seu nome em `MenuService` e `MovimentacaoService`).

---

Arquivos-chave a abrir rapidamente durante a defesa:
- `src/main/java/com/garagem/GaragemApplication.java`
- `src/main/java/com/garagem/service/MenuService.java`
- `src/main/java/com/garagem/service/MovimentacaoService.java`
- `src/main/resources/application.properties`
- `src/main/resources/data.sql` (dados iniciais)

Com este roteiro você consegue demonstrar funcionamento e responder perguntas do professor sobre onde cada funcionalidade está implementada.
