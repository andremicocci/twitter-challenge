![CI](https://github.com/andremicocci/twitter-challenge/workflows/CI/badge.svg)

# Case - Desafio Técnico

Este desafio consiste em criar uma aplicação para atender as funcionalidades mencionadas abaixo:
- Coletar as últimas postagens do Twitter, dada uma determinada hashtag, limitado a 100 ocorrências por hashtag.
	- As hashtags a serem coletadas são: #cloud, #container, #devops, #aws, #microservices, #docker, #openstack, #automation, #gcp, #azure, #istio, #sre
- Armazenar em banco de dados
- Através dos dados coletados, listar os 5 usuários com maior número de seguidores

# Tecnologias Utilizadas
Abaixo, estão relacionadas as tecnologias utilizadas para entrega da solução.
- IDE Spring Tool Suite 4
- Java 8 (openjdk:8-jdk-alpine)
- SpringBoot 2.2.4
- SpringBootActuator 2.2.4
- SpringBootDataMongoDB 2.2.4
- Twitter4J 4.0.6
- Maven 3
- Github
- Github Actions (CI - Build Java e Docker)
- DockerHub
- AWS ECS
- AWS Fargate
- AWS Application Load Balancer
- MongoDB 4.2.3 Community
- MongoDB Compass

# Diagrama da Solução
A solução desenhada foi baseada na arquitetura de microserviços. 
A partir de um commit na branch master do projeto no Github, são disparadas as ações listadas a seguir:
- O build, os testes unitários e o empacotamento da aplicação Java são realizados pelo Github Actions. 
 - A build da imagem docker e o push para o DockerHub também são realizados pelo Github Actions.
 - É realizado um estímulo via webhook ao AWS Fargate, para começar o processo de atualização da aplicação via Rolling Update.

# Links
## Github
[https://github.com/andremicocci/twitter-challenge](https://github.com/andremicocci/twitter-challenge)

## DockerHub
[https://hub.docker.com/r/andremicocci/twitter-challenge](https://hub.docker.com/r/andremicocci/twitter-challenge)

## API
**Buscando as hashtags no Twitter**
```sh
curl -X GET "localhost:8080/api/v1/tweets?q=%23cloud+%23container+%23devops+%23aws+%23microservices+%23docker+%23openstack+%23automation+%23gcp+%23azure+%23istio+%23sre"
```

**Buscando os top5 usuários com maior número de seguidores**
```sh
curl -X GET "localhost:8080/api/v1/tweets/top5Users"
```