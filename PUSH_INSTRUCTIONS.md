# Instruções para subir o projeto no Git (pronto para enviar à professora)

Siga estes passos no diretório do projeto local antes de subir para o repositório remoto.

1) Inicializar repositório (se ainda não existir):

```powershell
# inicializa o repositório (apenas uma vez)
git init

git add .

git commit -m "Entrega: Sistema de Gerenciamento de Garagem - versão para apresentação"
```

2) Criar repositório remoto (GitHub/GitLab/Bitbucket) e ligar ao remoto:

```powershell
# substitua <remote-url> pela URL do repositório remoto (ex: https://github.com/usuario/repo.git)
git remote add origin <remote-url>

git branch -M main

git push -u origin main
```

3) Arquivos importantes que já estão prontos para enviar (incluídos no ZIP de entrega):
- `pom.xml`
- `src/` (todo o código fonte)
- `docker-compose.yml`
- `Dockerfile`
- `mvnw`, `mvnw.cmd`, `.mvn/wrapper/maven-wrapper.jar` e `mvnw Bootstrap` (permite build sem Maven instalado)
- `README_MINIMO.md` (instruções de execução)
- `PRESENTATION.md` (roteiro e mapeamento dos arquivos)
- `src/main/resources/data.sql` (dados iniciais para demo)

4) Observações finais para a apresentação
- Recomendo subir todo o repositório e, no README, colocar instruções curtas repetindo o passo a passo.
- Se preferir, você pode enviar somente o `garagem-delivery.zip` criado na raiz (contém apenas os arquivos essenciais).

5) Se quiser que eu faça o commit localmente para você (não posso push), escolha a opção e eu criarei um commit com mensagem padrão.