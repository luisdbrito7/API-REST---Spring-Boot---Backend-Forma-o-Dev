# API REST - Gerenciamento de Produtos (Backend)

API RESTful desenvolvida em **Java** com **Spring Boot** para gerenciamento de produtos e autenticação de usuários com Spring Security e JWT.

---

## Tecnologias Utilizadas

* **Java 25**
* **Spring Boot 3.x**
* **Spring Security** (Autenticação e Autorização)
* **Java JWT (`com.auth0:java-jwt`)** (Tokens de acesso HmacSHA256)
* **Spring Data JPA** (Persistência de dados)
* **Flyway Migration** (Gerenciamento de versões do banco de dados)
* **Maven** (Gerenciador de dependências)

---

##Segurança e Autenticação

* **Filtro Customizado (`JwtFiltro`):** Intercepta as requisições HTTP para validar o token JWT enviado no cabeçalho `Authorization: Bearer <token>`.
* **CORS Configurado:** Permite a integração segura com a aplicação frontend (Next.js).
* **Tratamento Global de Exceções:** Captura e padroniza os erros de validação, autenticação e erros internos.

---

##Endpoints da API

### **Autenticação (`/usuarios`)**
* `POST /usuarios/cadastrar` - Cadastro de novos usuários.
* `POST /usuarios/login` - Autenticação e geração do token JWT.

### **Produtos (`/produtos`)** *(Requer autenticação)*
* `GET /produtos` - Lista todos os produtos.
* `GET /produtos/{id}` - Busca produto por ID.
* `POST /produtos` - Cadastra um novo produto.
* `PUT /produtos/{id}` - Atualiza um produto existente.
* `DELETE /produtos/{id}` - Remove um produto.

---

## Como Executar a Aplicação

### **Pré-requisitos**
* Java 25 instalado.
* Maven instalado (ou utilize o wrapper `./mvnw`).

