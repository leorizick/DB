# Desafio Votação
API REST desenvolvida com Spring Boot 4, MongoDB, Kafka, Docker e mensageria assíncrona para simular o fluxo de um sistema de votação.

## Tecnologias
- Java 25
- Spring Boot 4
- MongoDB
- Kafka + Zookeeper
- Docker Compose
- Spring Security (simplificado)
- WebClient (integração com serviço externo fake)

O sistema implementa:
- Criação de pautas (topics)
- Abertura de sessões de votação
- Votação em pautas com sessão aberta
- Envio de votos para o Kafka
- Persistência dos votos via consumer Kafka

## Decisões de Arquitetura
- Em um cenario real de produção, o consumer do kafka para realizar a votação seria um microsserviço a parte, assim podendo lidar melhor com a escalabilidade para um grande volume de dados.
Para evitar complexidade no teste, e facilitar a execução/avaliação assim como a estrutura do docker o consumer foi implementado na propria aplicação.
- Em projetos particulares costumo usar o Lombok para deixar as classes mais limpas evitando codigos como getters e setters, mas sei que tal pratica nao é bem vista em todas as empresas, então optei por deixa-las explicitas na aplicação.

## Fluxo do sistema

- Abrir um terminal na pasta raiz do sistema e executar o comando:
"docker compose up" para subir os containers do docker necessarios para rodar a aplicação.

- Antes de rodar a aplicação rodar o comando mvn clean install para que o maven installe as dependencias corretamente.

- Etapa 1:
  Criar uma pauta "/api/v1/topics"
  Exemplo de body:
   {
    "titulo": "Aprovação do orçamento 2025",
    "descricao": "Votação para aprovação do orçamento anual da cooperativa."
   }

- Etapa 2:
  Abrir sessão para pauta "/api/v1/topics/{topicId}/sessions
  Exemplo de body caso deseja definir uma duração:
  { "duration": 15 }

- Etapa 3:
  O sistema faz uma consulta num fake client para validar o cpf, essa validação é aleatoria e pode ou não retornar valido.

- Etapa 4:
  Enviar o voto para uma sessão de pauta ja aberta "/api/v1/topics/{topicId}/vote"
  Exemplo de body:
  {
    "cpf": "91273161068", //cpf aleatorio
    "vote": true
  }

- Etapa 5:
  É enviada a mensagem do voto para o kafka, e o consumer recebe essa mensagem e a persiste no banco.