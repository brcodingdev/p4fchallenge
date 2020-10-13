# Back

### Stack
- Java versão 11
- Spring Boot versão 2.3.4.RELEASE
- Spring Data
- Simple Cache
- Flyway migrations
- Swagger UI
- MySQL versão 14.14 Distrib 5.7.31, for Linux (x86_64)
- JWT Token
- Linux Ubuntu

### Como configurar
- Fazer o download do MySQL
- Criar banco de dados chamado p4f
- Entre na pasta back
- Configurar as credenciais do banco de dados src/main/resources/application.properties
- Digite o comando ./mvnw clean install

### Como executar
- Digite o comando ./mvnw spring-boot:run

### Info
- Coleção do postman https://www.getpostman.com/collections/2628d5ac3e2570746dc1
- Swagger UI http://localhost:8080/swagger-ui.html

### TODO
- Melhorar a documentaçao do swagger
- Melhorar o cache
- Inserir Docker
- Permissão somente de ADMIN para acesso da coleção users
- Testes de integração
- BDD com cucumber
- Sonar
- Lombok

# Front

### Stack
- Vue.js versão 2.6.10
- Template https://coreui.io
- jsonwebtoken
- axios
- vue-select

### Como configurar
- Entre na pasta front
- Digite o comando npm install


### Como executar
- Digite o comando npm run serve
- No navegador abra o endereço http://localhost:8081/
- Se autentique como administraodr, usuário: goku, senha: 123

# Info
- O menu Usuários só está com acesso ao usuário com permissão ADMIN

# TODO
- Testes com Jest
- Implementação do delete do endereço

