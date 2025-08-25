# Anotações que fiz para estudos, a maioria enquanto tive erros

- Você adicionou as dependências spring-boot-starter-data-jpa e spring-boot-starter-jdbc no seu arquivo pom.xml. Essas dependências dizem ao Spring que seu projeto vai interagir com um banco de dados. Quando a aplicação é iniciada, o Spring tenta configurar automaticamente essa conexão. No entanto, para se conectar a um banco de dados, ele precisa de dois componentes principais:

    O driver: A biblioteca específica para o tipo de banco de dados que você quer usar (por exemplo, MySQL, PostgreSQL, H2, etc.).

    A configuração da conexão: As informações como a URL do banco de dados, nome de usuário e senha.

- O H2 é um banco de dados relacional em memória escrito em Java. A principal vantagem dele é que não precisa de nenhuma instalação ou configuração complexa, porque ele roda dentro da própria aplicação. É ideal para ambientes de desenvolvimento e testes rápidos, já que é muito leve e inicia junto com a sua aplicação.

- spring.jpa.hibernate.ddl-auto: Esta propriedade é a que diz ao Hibernate (o motor por trás do Spring Data JPA) para gerenciar a estrutura do seu banco de dados. O valor update significa que, ao iniciar a aplicação, o Hibernate irá verificar o banco de dados e as entidades (sua classe Task). Se a tabela task não existir, ele a criará. Se você modificar a entidade Task (por exemplo, adicionando uma nova coluna), ele fará a alteração correspondente na tabela existente.

- create: Esta opção é mais agressiva. Ela deleta todas as tabelas e as recria a cada inicialização da aplicação. Isso é ótimo para testes, pois garante que você sempre comece com um banco de dados limpo. No entanto, você perde todos os dados a cada reinício.

- create-drop: Funciona como o create, mas com uma diferença: ele deleta as tabelas ao desligar a aplicação. É perfeito para testes unitários ou de integração, garantindo que o banco seja completamente limpo após o uso.

- none: Esta é a opção mais segura. Ela diz ao Hibernate para não fazer nada com a estrutura do banco de dados. Isso é crucial em ambientes de produção, onde a gestão do esquema (schema) deve ser feita de forma controlada e manual.

- Quem realmente cria e modifica as tabelas não é o Spring Boot, mas sim o Hibernate, que o Spring Data JPA usa como provedor. O Spring Data JPA é uma camada de abstração que simplifica o uso do JPA, mas a "magia" de mapear objetos para tabelas é feita pelo Hibernate.

- O Hibernate lê as anotações na sua classe Task (@Entity, @Table, @Id) e traduz essas informações para comandos SQL de criação de tabela, como CREATE TABLE task ( ... ). É ele quem envia esses comandos para o banco de dados

- Em um ambiente de produção, a alteração do esquema do banco de dados deve ser um processo controlado e reversível. Por isso, a melhor prática é usar ferramentas de migração de banco de dados.

- As migrações são scripts de SQL ou Java que definem a estrutura do banco de dados em um formato versionado. As duas ferramentas mais populares para isso são o Flyway e o Liquibase.

- Flyway: Você cria arquivos SQL com o prefixo V e um número de versão (V1.0__create_task_table.sql, V1.1__add_new_column.sql). O Flyway gerencia quais scripts já foram executados e aplica apenas os novos. Isso garante que o estado do banco de dados seja sempre o mesmo, independentemente de quem o esteja rodando.

- Liquibase: Funciona de forma semelhante, mas usa arquivos XML, YAML ou JSON para definir as alterações. Ele oferece mais flexibilidade e abstração em relação ao banco de dados específico.

- Em poucas palavras, um bean é um objeto que o Spring gerencia. A anotação @Bean é usada dentro de uma classe de configuração (como a sua classe principal anotada com @SpringBootApplication) para dizer ao Spring se encarregar de criar, configurar e gerenciar o ciclo de vida desse objeto. Em vez de você instanciá-lo manualmente com new, o Spring faz isso por você e o mantém pronto para ser injetado em outros lugares da aplicação.