# Agendamento de Exames no SUS

Projeto desenvolvido para o hackathon da FIAP, focado no agendamento de exames no SUS. O sistema visa otimizar a marcação de exames, facilitando o acesso dos pacientes e melhorando a gestão dos serviços de saúde.

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.3.x**
- **PostgreSQL**
- **Arquitetura MVC**
- **Microserviços**

## Funcionalidades do Sistema

- Cadastro e autenticação de usuários
- Agendamento de exames
- Consulta de exames agendados
- Cancelamento e reagendamento
- Notificações e lembretes
- Relatórios e dashboard para gestão

## Como Executar o Projeto

### Pré-requisitos

Antes de começar, certifique-se de ter os seguintes requisitos instalados:

- [JDK 17](https://adoptium.net/)
- [Maven](https://maven.apache.org/)
- [Docker](https://www.docker.com/) (opcional, para rodar o banco de dados em container)
- PostgreSQL configurado

### Passos para execução

1. Clone este repositório:
   ```sh
   git clone https://github.com/seu-usuario/seu-repositorio.git
   ```
2. Acesse o diretório do projeto:
   ```sh
   cd seu-repositorio
   ```
3. Configure o banco de dados PostgreSQL. Caso use Docker, você pode subir um container com:
   ```sh
   docker run --name postgres -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=admin -e POSTGRES_DB=agendamentos -p 5432:5432 -d postgres
   ```
4. Configure as variáveis de ambiente no arquivo `.env` ou no `application.properties`.
5. Execute a aplicação com o Maven:
   ```sh
   ./mvnw spring-boot:run
   ```
6. A API estará disponível em `http://localhost:8080`.

## Estrutura do Projeto

O projeto segue a Arquitetura MVC e está estruturado da seguinte forma:

```
/seu-repositorio
│-- src/main/java/com/seuusuario/susagendamentos
│   ├── controller  # Contém os controllers da API
│   ├── service     # Contém a lógica de negócios
│   ├── repository  # Contém a camada de persistência
│   ├── model       # Contém as entidades do banco
│-- src/main/resources
│   ├── application.properties  # Configurações do projeto
│-- Dockerfile  # Para containerizar a aplicação
│-- README.md  # Documentação do projeto
```

## Endpoints da API

### Autenticação

- `POST /auth/login` → Realiza login e retorna o token JWT.
- `POST /auth/register` → Registra um novo usuário.

### Agendamentos

- `POST /agendamentos` → Cria um novo agendamento.
- `GET /agendamentos` → Lista todos os agendamentos.
- `GET /agendamentos/{id}` → Busca um agendamento pelo ID.
- `PUT /agendamentos/{id}` → Atualiza um agendamento.
- `DELETE /agendamentos/{id}` → Cancela um agendamento.

## Contribuição

Sinta-se à vontade para contribuir com melhorias! Para contribuir:

1. Faça um fork do projeto
2. Crie uma branch para sua funcionalidade: `git checkout -b minha-feature`
3. Commit suas mudanças: `git commit -m 'Adicionando nova funcionalidade'`
4. Envie para o repositório: `git push origin minha-feature`
5. Abra um Pull Request

## Licença

Este projeto é de código aberto e segue a licença [MIT](LICENSE).
