# Autorizadora Fake para transações - API Cartão de crédito FIAP

Sistema que simula uma autorizadora de cartão de crédito para alunos da FIAP que conta com recursos como:
- Envia uma carga inicial com 1300 transações;
- Envia uma nova transacao a cada 5 segundos;

### Arquitetura 
![Tela Inicial](/doc/images/esquema.PNG)

### Setup

- Preenchar suas configurações no arquivo `application.yaml`.
```text
// src/main/resources/application.yaml
```
### Run
Execute na raiz do projeto a task `bootRun` do gradle.

- Windows
  `gradlew bootRun`
- Linux / Mac
  `./gradlew bootRun`
