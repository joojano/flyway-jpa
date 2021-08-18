# Flyway + JPA
<p align="center">
    <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white"/>
    <img src="https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white"/>
    <img src="https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white"/>
    <img src="https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white"/>
    <img src="https://img.shields.io/badge/Docker-0DB7ED?style=for-the-badge&logo=docker&logoColor=white"/>
</p>

### Tópicos

- [Descrição do projeto](#descricao)
- [O que foi usado?](#usado)
- [Como rodar a aplicação?](#rodar)
  - [Itens necessários para rodar a aplicação](#dependencias)
  - [Executando a aplicação](#executando-app)
  - [Executando as migrações](#executando-migrations)
- [Endpoints da aplicação](#endpoints)
- [Comandos do Flyway](#comandos-flyway)

<a name="descricao"/>

## Descrição do projeto
API REST básica para inserir e consultar usuários em um sistema, com ferramenta de migração de dados Flyway imbutida para versionamento de schema (migração de dados).

<a name="usado"/>

## O que foi usado?

- Java 8, com as seguintes dependências:
  - Spring Boot 2.4.2
  - Spring Data
  - [Lombok](https://projectlombok.org/) para redução de códigos comuns como getters/setters;
  - Driver PostgreSQL;
  - [Flyway](https://flywaydb.org/) com Maven Wrapper para migração de dados;
- PostgreSQL 13.4;
- Docker v20.10.8

<a name="rodar"/>

## Como rodar a aplicação?

<a name="dependencias"/>

### Itens necessários

- [Docker](https://docs.docker.com/desktop/);
- [Docker Compose](https://docs.docker.com/compose/install/)

<a name="executando-app"/>

### Executando a aplicação
A API foi pré-construída para ser consumida imediatamente, e empacotada em uma imagem Docker. Adicionalmente, 
a dependência da aplicação, PostgreSQL, está incluída no arquivo docker-compose.yml.

Para executar toda a aplicação, clone este repositório, abra seu terminal e vá até a pasta onde o repositório está. 
Em seguida, execute o seguinte comando:

```docker-compose up -d```

Após a execução bem sucedida, você poderá acessar a API no endereço http://localhost:10000 e o banco de dados no 
endereço localhost:5432.

<a name="executando-migrations"/>

### Executando as migrações

Para criação do banco de dados e dados de exemplo, são usados os arquivos .sql contidos na pasta 
src/main/resources/db/migration. Para implantar as migrações, vá até a pasta raiz e execute o seguinte comando:

```./mvnw flyway:migrate```

A aplicação irá implementar os SQLs no banco de dados.

**Vale ressaltar que o mvnw pode não ter permissão para rodar no seu sistema**. Caso esteja no Linux, execute o comando 
`chmod +x mvnw` para dar permissão de execução.

<a name="endpoints"/>

## Endpoints da aplicação
Considerando a simplicidade da aplicação, foi optado pela não criação da especificação OpenAPI/Swagger. Portanto, segue 
abaixo os métodos da API.

| Método | Caminho | Função                                   | Body                                           | Retorno                                                          |
|--------|---------|------------------------------------------|------------------------------------------------|------------------------------------------------------------------|
| POST   | /user   | Insere um novo usuário no banco de dados | { 	"username": "string", 	"password": "string" } | {   "id": uuid,   "username": string,   "password": string }     |
| GET    | /users  | Lista todos os usuários                  | Não aplicável                                  | [ {   "id": uuid,   "username": string,   "password": string } ] |

<a name="comandos-flyway"/>

## Comandos do Flyway
| Comando  |    Comando via mvnw    | Função                                                                                                                                                                                                                                                                                                                                                                                                                         |
|----------|:----------------------:|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| migrate  |  ./mvnw flyway:migrate | Parte principal do Flyway, migra o schema para a última versão especificada. Caso a base de dados não esteja versionada, o comando irá criar uma tabela chamada “flyway_schema_history” e irá realizar as operações que estão contidas nos arquivos .sql em ordem de número de versão, do menor para o maior número de versão. Todas as migrations executadas são salvas na tabela supracitada, incluindo o checksum em CRC32. |
| clean    |   ./mvnw flyway:clean  | Limpa tudo dentro do schema, incluindo tabelas, views, procedures, etc.                                                                                                                                                                                                                                                                                                                                                        |
| info     |   ./mvnw flyway:info   | Imprime detalhes e informação de status sobre todas as migrations, incluindo as que ainda não foram executadas.                                                                                                                                                                                                                                                                                                                |
| validate | ./mvnw flyway:validate | Valida as migrações já aplicadas são as mesmas que estão localmente.                                                                                                                                                                                                                                                                                                                                                           |
| baseline | ./mvnw flyway:baseline | Cria uma linha de base para uma base de dados já existente. Útil para bancos de dados que já existem. Depois do baseline, é possível realizar migrações normalmente.                                                                                                                                                                                                                                                           |
| repair   |  ./mvnw flyway:repair  | Repara a tabela de histórico de schema. Corrige falhas que aconteceram durante uma migração, podendo remover entradas de migração que falharam e “realinhar” checksums, descrições e tipos de migrações já aplicadas em relação às migrações locais.                                                                                                                                                                           |