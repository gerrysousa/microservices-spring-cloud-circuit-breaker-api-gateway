# microservices-spring-cloud-circuit-breaker-api-gateway
Curso de Microservices com Spring Cloud: Circuit Breaker, Hystrix e API Gateway

Passos para executar os Serviços:
Na pasta raiz executar o comando para criar o banco fornecedor:
```docker-compose up```

Executar os serviços na sequencia

- config-server
- eureka-server
- fornecedor
- loja


Criar registro:
Na tabela info_fornecedor:

```INSERT INTO `fornecedor`.`info_fornecedor` (`endereco`, `estado`, `nome`) VALUES ('Rua B', 'MG', 'Loja do Tião');```


Na tabela produto:

```
INSERT INTO `fornecedor`.`produto` (`descricao`, `estado`, `nome`, `preco`) VALUES ('Furadeira', 'GO', 'Fura Bosh', '150');
INSERT INTO `fornecedor`.`produto` (`descricao`, `estado`, `nome`, `preco`) VALUES ('Martelo', 'GO', 'Martelete', '20');
```


Agora vc já pode executas as requisições: 

```
curl --location --request POST 'http://localhost:8080/compra' \
--header 'Content-Type: application/json' \
--data-raw '{
    "itens": [
        {
            "id": 1,
            "quantidade": 5
        },
         {
            "id": 2,
            "quantidade": 1
        }
    ],
    "endereco":{
        "rua": "Rua W",
        "numero": 123,
        "estado": "MG"
    }
}'
```