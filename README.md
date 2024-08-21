# JAVA_MICROSERVICE

## INTRODUCTION

The development of microservices-based applications has become an increasingly popular approach in building distributed systems, offering flexibility, scalability, and high availability. The final project of the Java course at EBAC (Escola Britânica de Artes Criativas) embraces this innovative approach, proposing the creation of a system composed of three interconnected microservices: Memes, Users, and Meme Categories.

Each of these microservices—Memes, Users, and Meme Categories—is independent and can be initialized autonomously, allowing for more efficient maintenance and scalability. However, the true power of this system lies in its ability to work harmoniously, as these microservices are interconnected to create a complete and rich experience for users.

For Service Discovery, Spring Cloud ZooKeeper was used, enabling the three microservices (Memes, Users, and Meme Categories) to find and interact with each other dynamically, scalably, and resiliently. This is crucial for creating distributed systems based on microservices, where flexibility and the ability to adapt to changes in infrastructure are essential.

Additionally, tracing with OpenZipkin is a valuable addition to your project, providing detailed insights into the behavior and performance of your interconnected microservices, working in conjunction with Service Discovery to create a robust and highly monitored distributed system.

## QUICKSTART

A separate application.properties file was created for each service; therefore, when opening them, they should be opened in a new window, opening each service's folder separately.

### Zipkin

Run on command line interface:
`docker run -d -p 9411:9411 openzipkin/zipkin`

### Apache Zookeeper

Create the docker-compose.yml file below and type docker compose up in the command line within the directory where the .yml file is located.
```
version: '3.1'

services:
  zoo1:
    image: zookeeper
    restart: always
    hostname: zoo1
    ports:
      - 2181:2181
    environment:
      ZOO_MY_ID: 1
      ZOO_SERVERS: server.1=zoo1:2888:3888;2181 server.2=zoo2:2888:3888;2181 server.3=zoo3:2888:3888;2181

  zoo2:
    image: zookeeper
    restart: always
    hostname: zoo2
    ports:
      - 2182:2181
    environment:
      ZOO_MY_ID: 2
      ZOO_SERVERS: server.1=zoo1:2888:3888;2181 server.2=zoo2:2888:3888;2181 server.3=zoo3:2888:3888;2181

  zoo3:
    image: zookeeper
    restart: always
    hostname: zoo3
    ports:
      - 2183:2181
    environment:
      ZOO_MY_ID: 3
      ZOO_SERVERS: server.1=zoo1:2888:3888;2181 server.2=zoo2:2888:3888;2181 server.3=zoo3:2888:3888;2181
```

## HTTP POST REQUESTS

### Usuário

`http://localhost:8083/usuarios`

```
{
	"nome": "Pikachu",
	"email": "pika@mail.com",
	"dataCadastro": "2024-01-01"
}
```

### Categorias Memes

`http://localhost:8082/categorias-meme`

```
{
	"nome": "Pokemons",
	"descricao": "Categoria de Pokemons",
	"dataCadastro": "2024-01-01",
	"usuarioId": 1
}
```

### Memes

`http://localhost:8083/usuarios`

```
{
	"nome": "Meme do Pikachu",
	"descricao": "Engraçado",
	"dataCadastro": "2024-01-01",
	"url": "https://www.techtudo.com.br/noticias/2012/04/o-que-e-meme.ghtml",
	"usuarioId": 1,
	"categoriaMemeId": 1
}
```

## HTTP GET REQUESTS

`http://localhost:8083/usuarios`
`http://localhost:8082/categorias-meme`
`http://localhost:8081/memelandia`

### TRACINGS

## Creating category

![Tracing categoria](/images/categoriameme.png)

## Creating meme

![Tracing meme](/images/meme.png)
