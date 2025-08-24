# Anotações que fiz para estudos, a maioria enquanto tive erros

- Você adicionou as dependências spring-boot-starter-data-jpa e spring-boot-starter-jdbc no seu arquivo pom.xml. Essas dependências dizem ao Spring que seu projeto vai interagir com um banco de dados. Quando a aplicação é iniciada, o Spring tenta configurar automaticamente essa conexão. No entanto, para se conectar a um banco de dados, ele precisa de dois componentes principais:

    O driver: A biblioteca específica para o tipo de banco de dados que você quer usar (por exemplo, MySQL, PostgreSQL, H2, etc.).

    A configuração da conexão: As informações como a URL do banco de dados, nome de usuário e senha.

- O H2 é um banco de dados relacional em memória escrito em Java. A principal vantagem dele é que não precisa de nenhuma instalação ou configuração complexa, porque ele roda dentro da própria aplicação. É ideal para ambientes de desenvolvimento e testes rápidos, já que é muito leve e inicia junto com a sua aplicação.

