# 📚 Sistema de Gerenciamento Biblioteca — Spring Boot

API REST completa para gerenciamento de biblioteca, desenvolvida em Java com Spring Boot,
aplicando arquitetura em camadas, DTOs, tratamento de exceções customizado e regras de negócio reais.

Projeto construído durante estudo aprofundado de Spring Boot, Spring Data JPA e boas práticas
de desenvolvimento de APIs REST.

---

## 🚀 Funcionalidades

- **Gestão de Autores, Livros e Membros** — CRUD completo
- **Sistema de empréstimo e devolução** de livros
- **Controle automático de estoque** — desconta ao emprestar, devolve ao retornar
- **Limite máximo de empréstimos ativos** por membro
- **Bloqueio de empréstimo duplicado** — um membro não pode ter dois empréstimos do mesmo livro simultaneamente
- **Cálculo automático de atraso** na devolução
- **Tratamento de erros centralizado** — exceções customizadas com respostas HTTP semânticas (404 para recurso não encontrado, 400 para violação de regra de negócio)

---

## 🛠️ Tecnologias

- **Java 25**
- **Spring Boot** (Spring Web, Spring Data JPA)
- **PostgreSQL**
- **Maven**
- **Lombok**
- **Postman** (testes de API)

---

## 🏗️ Arquitetura

O projeto segue uma estrutura em camadas, separando responsabilidades:
```
src/main/java/br/study/SpringStudy_3/
├── Controller/ → Exposição dos endpoints REST
│ └── ControllerAdvice/ → Tratamento global de exceções
├── Entity/ → Mapeamento das tabelas (JPA)
├── Exception/ → Exceções customizadas
│ └── ErrorMessages/ → Mensagens de erro padronizadas
├── Repository/ → Acesso a dados (Spring Data JPA)
└── Service/ → Regras de negócio
├── DTO/ → Objetos de transferência de dados
└── Enum/ → Enumerações (status de empréstimo)
```

**Por que DTOs?** As entidades JPA nunca são expostas diretamente na API — cada endpoint
usa DTOs específicos de entrada e saída, evitando exposição de dados sensíveis, prevenindo
mass assignment e desacoplando o modelo de banco de dados do contrato da API.

---

## 📋 Principais Endpoints

| Método | Rota                                       | Descrição |
|--------|--------------------------------------------|-----------|
| POST | `/membro/cadastro`                         | Cadastra um novo membro |
| GET | `/membro`                                  | Lista todos os membros |
| POST | `/livro/cadastro`                          | Cadastra um novo livro |
| GET | `/livro`                                   | Lista todos os livros |
| POST | `/emprestimo/registrar`                    | Registra um novo empréstimo |
| GET | `/emprestimo/membro/{id}`                  | Consulta empréstimos de um membro |
| PUT | `/emprestimo/devolver/{idBook}/{idMember}` | Registra a devolução de um livro |

---

## ⚙️ Como executar localmente

**Pré-requisitos:** Java 25, Maven, PostgreSQL

1. Clone o repositório:
```bash
   git clone https://github.com/seu-usuario/nome-do-repo.git
```

2. Crie um banco de dados PostgreSQL

3. Copie o arquivo de exemplo de configuração e preencha suas credenciais:
```bash
   cp src/main/resources/application.properties.example src/main/resources/application.properties
```

4. Execute a aplicação:
```bash
   ./mvnw spring-boot:run
```

5. A aplicação estará disponível em `http://localhost:8080`

---

## 📮 Testando com Postman

O repositório inclui uma collection do Postman com todas as requisições já configuradas.

1. Abra o Postman
2. Importe os arquivos da pasta `postman/` deste repositório
3. Configure a variável de ambiente `base_url` como `http://localhost:8080`
4. Teste os endpoints diretamente pela collection importada

---

## 🧠 O que este projeto me ensinou

- Modelagem de relacionamentos JPA (`@ManyToOne`, `@OneToMany`) e prevenção de referência circular
- Separação de responsabilidades entre Controller, Service e Repository
- Diferença entre validação de existência (404) e validação de regra de negócio (400)
- Uso de transações (`@Transactional`) para garantir consistência em operações compostas
- Query methods do Spring Data JPA (derivação automática de queries por nome de método)