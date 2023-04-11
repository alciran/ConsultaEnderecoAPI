# API Consulta Endereço

<a><img src="https://img.shields.io/static/v1?label=Java&message=version11&color=blue" alt="Java 11"/></a>
<a><img src="https://img.shields.io/static/v1?label=SpringBoot&message=2.7.0&color=yellow" alt="Spring Boot 2.7.0"/></a>
<a><img src="https://img.shields.io/static/v1?label=Cucumber&message=Pass&color=brightgreen" alt="Cucumber Pass"/></a>

## Descrição
Projeto com implementação de uma API para retorno de endereço e cálculo de frete para um determinado CEP.

- Java 11
- Maven 3.6.0
- Cucumber 6.10.3

## Como usar
Clonar o repositório:

```
git clone https://github.com/alciran/ConsultaEnderecoAPI.git
```

Como executar:

- _Linha de comando:_ `mvn clean spring-boot:run`
- _Utilizando IDE:_ executando o método main da classe `ConsultaEnderecoApplication`.

Como fazer build:

./build.sh "stage"

## Documentação da API
Documentação da API com Swagger:

- [localhost:8080/swagger-ui.html](localhost:8080/swagger-ui.html)
- [http://localhost:8080/v2/api-docs](http://localhost:8080/v2/api-docs)

Screenshot exemplo:

![api_doc_swagger](/src/assets/api_doc_swagger.png?raw=true "API Doc Swagger")

## Cucumber Relatório
No [arquivo properties ](https://github.com/alciran/ConsultaEnderecoAPI/blob/main/src/test/resources/junit-platform.properties) é possivel editar a configuração de testes, e criação de relatório com Cucumber.


Screenshot exemplo:

![cucumber_relatorio](/src/assets/cucumber_relatorio.png?raw=true "Cucumber Relatório")







