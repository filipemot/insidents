# Incident Management API

Este é um projeto de API RESTful para gerenciamento de incidentes, desenvolvido com Spring Boot, Spring Data JPA e H2 Database. A API permite a criação, atualização, exclusão e listagem de incidentes.

## Funcionalidades

- Cadastrar Incidentes
- Atualizar Incidentes
- Deletar Incidentes
- Listar todos os incidentes
- Listar incidente por ID
- Listar os últimos 20 incidentes ordenados por ordem decrescente

## Tecnologias Utilizadas

- Java 11 ou superior
- Spring Boot
- Spring Data JPA
- H2 Database

## Pré-requisitos

- Java JDK 11 ou superior
- Maven
- IDE (opcional, mas recomendado) - IntelliJ IDEA, Eclipse, ou Spring Tool Suite

## Instalação

1. **Clone o repositório**:

   ```bash
   git clone https://github.com/seu_usuario/seu_repositorio.git
   cd seu_repositorio
   ```
   
2. **Abra o projeto em sua IDE.**

3. **Adicione as dependências necessárias no arquivo pom.xml (caso ainda não estejam incluídas).**

4. **Configure o banco de dados H2 no arquivo src/main/resources/application.properties (já configurado por padrão).**

5. **Execute a aplicação:**
```bash
mvn spring-boot:run
```
## Acessando a API
A API estará disponível em http://localhost:8080/api/incidents.

## Exemplos de Endpoints
- Criar um incidente:

  - Método: POST
  - Endpoint: /api/incidents
  - Corpo da requisição:

```json
    {
      "name": "Incidente 1",
      "description": "Descrição do incidente 1"
    }
```

- Listar todos os incidentes:

  - Método: GET
  - Endpoint: /api/incidents

- Obter um incidente por ID:
  - Método: GET
  - Endpoint: /api/incidents/{id}

- Atualizar um incidente:

- Método: PUT
- Endpoint: /api/incidents/{id}
- Corpo da requisição:
```json
{
    "name": "Incidente Atualizado",
    "description": "Descrição atualizada do incidente"
}
```
- Deletar um incidente:
  - Método: DELETE
  - Endpoint: /api/incidents/{id}

- Listar os últimos 20 incidentes:
  - Método: GET
  - Endpoint: /api/incidents/latest

  
## Testes
Para executar os testes unitários, use o comando:

```bash
mvn test
```

## Contribuição
Contribuições são bem-vindas! Sinta-se à vontade para abrir issues ou enviar pull requests.

## Licença
Este projeto está licenciado sob a Licença MIT. Veja o arquivo LICENSE para mais detalhes.
