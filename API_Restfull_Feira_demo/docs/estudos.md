# Estudos

## mapeamento objeto-relacional

O mapeamento objeto-relacional foi criado para abstrair as diferenças entre o modelo relacional e o paradigma orientado a objetos. Assim, deixa de ser necessário criarmos soluções com o intuito de converter dados em objetos e vice-versa. em Java, após a especificação JPA, isso passou a ser feito pelos frameworks que a implementam, como o Hibernate.

**@Entity**
A anotação @Entity informa ao JPA que uma classe é uma entidade que representa uma tabela no banco de dados, logo a JPA estabelecerá uma conexão entre a entidade e a tabela no banco de dados. Cada intancia desta entidade é uma linha na tabela.
**@Id**
Já a anotação @Id diz qual campo da entidade é a chave primária da tabela de bancos de dados
**@Table**
diz qual o nome da tabela que a nossa entidade representa no banco
**@GeneratedValue**
A anotação @GeneratedValue especifica que o valor do identificador de entidade é gerado automaticamente, seu parametro diz o tipo que este valor é gerado como por exemplo:
**strategy = GenerationType.IDENTITY**
Especifica a estratégia de geração. No caso de IDENTITY, o provedor de persistência (como o Hibernate) delega a responsabilidade de gerar o valor para o banco de dados, que usará suas colunas de auto-incremento para atribuir um ID único

## lombock

lombock uma ferramenta útil para diminuir a verbosidade no java
simplificação no código, as anotações Getter e Setter geram os métodos
getters e setters, padrões em entidades, 1 para cada atributo.
Já as anotações AllargsConstuctor e NoArgsConstructor servem para gerar
um construtor para a classe com argumentos(todos os atributos) e outros sem
A anotação EqualsAndHashCode é usada para gerar os métodos equals e hashCode
usando um parâmetro padrão para as comparações e para o cálculo hash

```java
@AllArgsConstructor 
@NoArgsConstructor 
@Getter 
@Setter 
@EqualsAndHashCode(of = "id")
```

## organização do código no pacote principal (demo)

**model/**
Nossas classes entidades que são mapeadas para o banco de dados vão aqui

**repository/**
JpaRepository<T, ID> é uma interface com métodos de consultas já implementados

alguns destes métodos que ganhamos é:
findAll() → lista todos os livros
findById(Long id) → busca por ID
save(Livro livro) → salva ou atualiza
deleteById(Long id) → deleta por ID
existsById(Long id) → verifica se existe

T é uma entidade, criada no model/
ID é o tipo da chave primária, mas precisa ser uma classe, não um tipo primitivo como long.

o Spring Data JPA vai gerar automaticamente os métodos de acesso ao banco

**service/**
    lógica de negócio, serve de intermediária entre o controller e o repository
    @Service é a anotação que diz que aquela classe é um service.

## tratando erros

Em aplicações Spring Boot bem estruturadas, o tratamento de erros geralmente começa no Service, mas a responsabilidade por transformar esses erros em respostas HTTP compreensíveis pertence à Controller.

por exemplo no nosso service teriamos 2 maneiras de fazer isso quando queremos por exemplo pegar um livro do repository com id

**orElseThrow( () -> new )**
```java
return repository.findById(id)
.orElseThrow(() -> new RuntimeException("Livro com ID " + id + " não encontrado"));
```

**Optional<> + throw new**
```java
Optional<Livro> obj = repository.findById(id);
if(obj.isPresent()){
    return obj.get();
}
throw new RuntimeException("Livro com o " + id + " Não foi encontrado!");
```  

no nosso controller poderiamos simplesmente colocar try catchs para cada método, para cada endpoint, mas o mais recomendado é criar uma classe própria para isso usando a seguinte anotação na nossa camada de controller

```java
@ControllerAdvice
public class LivroControlerException {  }
```

logo poderiamos tratar estes erros usando a anotação @ExceptionHandler acima do método desta classe que definimos para controlar os erros com o tipo de exceção

```java
@ExceptionHandler(RuntimeException.class)
public ResponseEntity<?> handleRuntimeException(RuntimeException e){
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
    .body(e.getMessage());
}
```

## criando exceções personalizadas

**DemoApplication.java**
