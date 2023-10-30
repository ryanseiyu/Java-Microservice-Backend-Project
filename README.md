# EBAC_JAVA_PROJETOFINAL

## INTRODUÇÃO

O desenvolvimento de aplicações baseadas em microserviços tem se tornado uma abordagem cada vez mais popular na construção de sistemas distribuídos, proporcionando flexibilidade, escalabilidade e alta disponibilidade. O projeto final do curso de Java na EBAC (Escola Britânica de Artes Criativas) abraça essa abordagem inovadora, propondo a criação de um sistema composto por três microserviços interconectados: Memes, Usuários e Categorias de Memes.

Cada um desses microserviços, a saber, Memes, Usuários e Categorias de Memes, é independente e pode ser inicializado de forma autônoma, permitindo uma manutenção e escalabilidade mais eficiente. No entanto, o verdadeiro poder desse sistema reside na sua capacidade de trabalhar de maneira harmoniosa, uma vez que esses microserviços estão interligados para criar uma experiência completa e rica para os usuários.

Para a Descoberta de Serviços foi utilizado o Spring Cloud ZooKeeper que permite que seus três microserviços (Memes, Usuários e Categorias de Memes) encontrem e interajam uns com os outros de forma dinâmica, escalável e resiliente. Isso é fundamental para criar sistemas distribuídos baseados em microserviços, onde a flexibilidade e a capacidade de adaptação a mudanças na infraestrutura são essenciais.

Adicionalmente o rastreamento com o OpenZipkin é uma adição valiosa ao seu projeto, fornecendo insights detalhados sobre o comportamento e o desempenho de seus microserviços interconectados, trabalhando em conjunto com a Descoberta de Serviços para criar um sistema distribuído robusto e altamente monitorado.

## QUICKSTART

Foi feito um arquivo application.properties para cada serviço portanto quando for abri-los, eles devem ser abridos em uma nova janela, abrindo o folder de cada serviço separadamente.

### Zipkin

Rodar na linha de comando:
`docker run -d -p 9411:9411 openzipkin/zipkin`

### Apache Zookeeper

Criar arquivo o docker-compose.yml abaixo e digitar `docker compose up` na linha de comando dentro do diretório aonde está o arquivo yml.

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

## Criação de categoria

![Tracing categoria](/images/categoriameme.png)

## Criação de meme

![Tracing meme](/images/meme.png)
