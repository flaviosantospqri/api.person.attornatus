# Challenge - Attornatus API de Pessoas.

O projeto consiste em um API de gerênciamento de pessoas e seus respectivos endereço. Não existe um contexto específico para uso, mas é possível utilizar para base de dados com foco em gerenciamento clientes e endereços para entrega.

# :rocket: Primeiras instruções.

Estas instruções, vão te guiar para obter a melhor experiência com a API. 

## :bookmark_tabs: Pré-requisitos


|       Necessário       | Link/Sugestão                                                |
| ----------------- | ---------------------------------------------------------------- |
| Uma IDE       | https://www.jetbrains.com/idea/download/#section=windows |
| Git       | https://git-scm.com/doc |
| JDK versão 11       | https://www.oracle.com/br/java/technologies/downloads/#java11 |
| Java       | https://www.java.com/pt-BR/download/ie_manual.jsp?locale=pt_BR |

# :heavy_check_mark: Consumindo o Serviço

### Para consumir localmente utilize alguma ferramente client e com a aplicação rodando, acesse:
```url
localhost:8080/person
```
### Importante

Pode ser que sua aplicação não esteja rodando na porta 8080, neste caso substitua na url 8080, pela porta em que a aplicação esteja rodando. Verifique o log para encontrar a porta correta. 

## Você pode consumir remotamente:

```url
apipersonattornatus-production.up.railway.app/person
```


## :green_book: Documentação da API

#### Cria Uma Pessoa

```http
POST /person
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| Body Request | Objeto JSON | Obrigatorio um objeto Json como parâmetro da requisição |

### Exemplo

```Json
"name":"Jhon",
"birthDate":"2022-04-05"
```

#### Retorna uma lista de Pessoas

```http
GET /person/find-all
```

### Exemplo
Retorno experado:

#### Lista populada
```Json
[
{
  "externalUUID": "68e60de8-879c-4b4a-b541-395f9c030131",
  "number": 242,
  "city": "Java City",
  "publicPlace": "Rua 1"
},
{
  "externalUUID": "68e60de8-879c-4b4a-b541-395f9c030101",
  "number": 242,
  "city": "Java City",
  "publicPlace": "Rua 2"
}
]
```
#### Lista vazia

```Json
[]
```


#### Retorna uma Pessoa

```http
GET /person/{uuid}/find-one
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `uuid`      | `string` | **Obrigatório**. O uuid da Pessoa que está buscando |

#### Exemplo

```url
person/5e98cd7b-1adc-4a40-a338-7dd3ba66efe3/find-one
```

#### Caso de Sucesso

```json
{
"name": "Jhon",
"uuid": "5e98cd7b-1adc-4a40-a338-7dd3ba66efe3",
"birthDate": "2022-04-05",
"addresses": [
  {
    "externalUUID": "68e60de8-879c-4b4a-b541-395f9c030131",
    "publicPlace": "Rua 1",
    "zipCode": "36610-000",
    "number": 242,
    "city": "Java City",
    "main": true
  }
]
}
```

#### Caso de Falha

```Json
Person not found, review uiid.
```


#### Retorna uma Pessoa atualizada

```http
PATCH /person/{uuid}/update
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `uuid`      | `string` | **Obrigatório**. O uuid da Pessoa que está buscando |

No corpo da requisição informar o valor que irá sofrear a modficação.

#### Exemplo

```Json
{
"name":"Junario",
"birthDate":"2022-04-05"
}
```

#### Caso de Sucesso

```json
{
"name": "Junario",
"uuid": "5e98cd7b-1adc-4a40-a338-7dd3ba66efe3",
"birthDate": "2022-04-05",
"addresses": [
{
  "externalUUID": "68e60de8-879c-4b4a-b541-395f9c030131",
  "publicPlace": "Rua 1",
  "zipCode": "36610-000",
  "number": 242,
  "city": "Java City",
  "main": true
}
]
}
```

#### Caso de Falha

```Json
Person not found, review uiid.
```


```http
POST /person/{uuid}/add-address
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `uuid`      | `string` | **Obrigatório**. O uuid da Pessoa que irá receber o endereço |


#### Exemplo

```Json
{
"publicPlace":"Rua dos Deves",
"zipCode":"36610-000",
"number":242,
"city":"Java City"
}
```

#### Caso de Sucesso

```json
{
"name": "Junario",
"uuid": "5e98cd7b-1adc-4a40-a338-7dd3ba66efe3",
"birthDate": "2022-04-05",
"addresses": [
  {
    "externalUUID": "8cf4ff8f-7c74-4acd-a7ea-302c69f39300",
    "publicPlace": "Rua dos Deves",
    "zipCode": "36610-000",
    "number": 242,
    "city": "Java City",
    "main": null
  }
]
}
```

#### Caso de Falha

```Json
Person not found, review uiid.
```

```http
GET person/{uuid}/find-all-address
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `uuid`      | `string` | **Obrigatório**. O uuid da Pessoa que irá receber o endereço |


#### Exemplo
```url
person/5e98cd7b-1adc-4a40-a338-7dd3ba66efe3/find-all-address
```

#### Caso de Sucesso

```json
[
{
  "externalUUID": "68e60de8-879c-4b4a-b541-395f9c030131",
  "number": 242,
  "city": "Java City",
  "publicPlace": null
}
]
```
#### Caso de Falha

```Json
Person not found, review uiid.
```

```http
PUT localhost:8080/person/{uuid-person}/main-address/{uuid-address}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `uuid-person`, uuid-address      | `string` | **Obrigatório**. uuid-person e uuid-address para localização do endereço na lista da pessoa cadastrada |


#### Exemplo
```url
localhost:8080/person/5e98cd7b-1adc-4a40-a338-7dd3ba66efe3/main-address/8cf4ff8f-7c74-4acd-a7ea-302c69f39300
```

#### Caso de Sucesso

```json
  {
    "name": "Junario",
    "uuid": "5e98cd7b-1adc-4a40-a338-7dd3ba66efe3",
    "birthDate": "2022-04-05",
    "addresses":
   [
    {
      "externalUUID": "8cf4ff8f-7c74-4acd-a7ea-302c69f39300",
      "publicPlace": "Rua dos Dfgeves",
      "zipCode": "36610-000",
      "number": 242,
      "city": "Java City",
      "main": true
    },
    {
      "externalUUID": "8cf4ff8f-7c74-4acd-a7ea-302c79f39300",
      "publicPlace": "Rua dos Dfgeves",
      "zipCode": "36610-000",
      "number": 242,
      "city": "Java City",
      "main": false
    }
  ]
}
```
#### Caso de Falha

```Json
Person not found, review uiid-person.
```
ou 
```Json
Address not found, review uiid-adress.
```

📄 Documentação Swagger
Com a aplicação rodando em sua máquina, você pode consultar a [documentação completa](http://localhost:8080/swagger-ui/index.html)



🛠️ Construído com
´´´List
* Java - Linguagem usado
* Spring Boot - FrameWork usado
* Maven - Gerente de Dependência
* Swagger - Documentação da API


## Autores

Desenvolvedor Back End - [@flaviosantospqri](https://github.com/flaviosantospqri)
Autoria - Attornatus

