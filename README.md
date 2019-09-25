Encurtador de URLs
===========================
Projeto de encurtador de URL construído com Spring Tool Suite + Java 8 + Swagger

## Ambientes
- Local: http://localhost:8080
- Heroku: https://encurtador-url.herokuapp.com (**disponível**)

## Instruções
- Dias de expiração da URL: configurado no arquivo application.properties.
- Utilizar o Postman para realização dos testes.
- Swagger: http://localhost:8080/swagger-ui.html

## Endpoints

#### 1. Exemplo para encurtar => `/urlshortener`
**POST** => http://localhost:8080/urlshortener
``` 
{
  "urlLong": "http://www.globo.com"
}
```
#### Retorno:
``` 
{
    "data": {
        "id": 1,
        "urlLong": "http://www.globo.com",
        "urlShort": "http://localhost:8080/urlshortener/4e170ade3d18de88d8bca24",
        "creationDate": "2019-09-25T03:59:56.873+0000",
        "expirationDate": "2019-09-24T03:59:56.854+0000"
    },
    "erros": []
}
```
**Observação: A estrutura de retorno contém o objeto Url dentro do data, além da possível lista de erros.**


#### 2. Exemplo para abrir a URL encurtada e fazer o redirecionamento => `/urlshortener/{hashCodeUrl}`
**GET** => http://localhost:8080/urlshortener/28d1510bc3c4ce681aeedab
##### Dica: Aqui podes fazer a chamada direto no navegador para ver o redirecionamento.


#### 3. Exemplo para buscar pelo ID a URL encurtada => `/urlshortener/id/{id}`
**GET** => http://localhost:8080/urlshortener/id/1
#### Retorno:
``` 
{
    "data": {
        "id": 1,
        "urlLong": "http://www.globo.com",
        "urlShort": "http://localhost:8080/urlshortener/4e170ade3d18de88d8bca24",
        "creationDate": "2019-09-25T03:59:56.873+0000",
        "expirationDate": "2019-09-24T03:59:56.854+0000"
    },
    "erros": []
}
```
