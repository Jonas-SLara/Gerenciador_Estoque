# Testando API REST com o Curl no terminal Linux ou WSL

## Introdução

`curl` é uma ferramenta de linha de comando para transferir dados via URLs, ideal para testar APIs REST de forma rápida e leve diretamente no terminal.

Por padrão, o comando `curl` realiza uma requisição HTTP **GET**.

Se quiser examinar mais detalhes da requisição e resposta, adicione a flag `-v`:

```bash
curl -v https://jsonplaceholder.typicode.com/todos/1
```

## Comandos curl e métodos HTTP

-X [HTTP_METHOD]
Define o método HTTP usado (GET, POST, PUT, DELETE, etc.)

-H [HTTP_HEADER]
Define os cabeçalhos HTTP (ex: tipo de conteúdo como JSON)

-d [YOUR_DATA]
Envia dados no corpo da requisição (para POST e PUT)

## Base da URL da API, usando a porta 8080 do host

```bash
http://localhost:8080/livros
```

### 📘 Criar um livro (POST)

```bash
curl -X POST http://localhost:8080/livros \
-H "Content-Type: application/json" \
-d '{"nome": "Dom Casmurro"}'
```

### 📚 Listar todos os livros (GET)

```bash
    curl http://localhost:8080/livros
```

### 🔍 Buscar um livro por ID (GET)

```bash
    curl http://localhost:8080/livros/1
```

### ✏️ Atualizar um livro (PUT)

```bash
curl -X PUT http://localhost:8080/livros/1 \
-H "Content-Type: application/json" \
-d '{"nome": "Memórias Póstumas de Brás Cubas"}'
```

### 🗑️ Deletar um livro (DELETE)

```bash
curl -X DELETE http://localhost:8080/livros/1
```

### 🧭 Ver opções disponíveis no endpoint (OPTIONS)

```bash
curl -v -X OPTIONS http://localhost:8080/livros
```

## Próximos passos

Entendo esta base com o curl pode-se utilizar outros meios para testar uma API rest como por exemplo, usando a ferramenta POSTMAN ou uma extensão do visual studio code que envia requisições http para o servidor no endpoint por meio de um arquivo com a extensão http