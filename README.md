# Desafio Votação

* API REST desenvolvida com Spring Boot 4, MongoDB, Kafka, Docker e mensageria assíncrona para simular o fluxo de um sistema de votação.
* O sistema consta com endpoints para o aplicativo mobile realizar requisições onde são devolvidos os campos necessarios para o formulario ser criado.

## Tecnologias

* Java 25
* Spring Boot 4
* MongoDB
* Kafka + Zookeeper
* Docker Compose
* Spring Security (simplificado)
* WebClient (integração com serviço externo fake)

O sistema implementa:

* Criação de pautas (topics)
* Abertura de sessões de votação
* Votação em pautas com sessão aberta
* Envio de votos para o Kafka
* Persistência dos votos via consumer Kafka

## Decisões de Arquitetura

* Em um cenario real de produção, o consumer do kafka para realizar a votação seria um microsserviço a parte, assim podendo lidar melhor com a escalabilidade para um grande volume de dados.
    Para evitar complexidade no teste, e facilitar a execução/avaliação assim como a estrutura do docker o consumer foi implementado na propria aplicação.
* Em projetos particulares costumo usar o Lombok para deixar as classes mais limpas evitando codigos como getters e setters, mas sei que tal pratica nao é bem vista em todas as empresas, então optei por deixa-las explicitas na aplicação.
* Em projetos reais, costumo usar duas camadas de services, geralmente uma service para api que é uma orquestradora de chamadas, chamando a service da camada de domain e fazendo por exemplo o mapper da entidade para o response DTO. Nesta aplicação foi usado somente uma camada para agilizar a programação.

## Fluxo do sistema

* Abrir um terminal na pasta raiz do sistema e executar o comando:
    `"docker compose up"` para subir os containers do docker necessarios para rodar a aplicação.

* Antes de rodar a aplicação rodar o comando `mvn clean install` para que o maven installe as dependencias corretamente.

* A aplicação implementa uma camada de UI nas controllers que tem a função de fornecer ao aplicativo mobile as telas no formato JSON, esse json contem o id do campo seu tipo, sua descrição e valor alem dos botões de ação que contem as urls de callback. A url de callback pode ser alterada no application.properties.

* **Etapa 1:**
    * Api mobile de retorno de campos do formulario de criação de topicos: `"GET /api/v1/ui/topics/form"`
    * Criar uma pauta `"POST /api/v1/topics"`
    * Exemplo de body:
        ```json
        {
          "titulo": "Aprovação do orçamento 2025",
          "descricao": "Votação para aprovação do orçamento anual da cooperativa."
        }
        ```

* **Etapa 2:**
    * Api Mobile para listar pautas: `"GET /api/v1/ui/topics"`
    * Api Mobile para abrir sessão de uma pauta: `"GET /api/v1/ui/topics/{topicId}/session/open"`
    * Abrir sessão para pauta `POST"/api/v1/topics/{topicId}/sessions"`
    * Exemplo de body caso deseja definir uma duração:
        ```json
        { "duration": 15 }
        ```

* **Etapa 3:**
    * O sistema faz uma consulta num fake client para validar o cpf, essa validação é aleatoria e pode ou não retornar valido.

* **Etapa 4:**
    * Api mobile de retorno de campos para votar em um topico `"GET /api/v1/ui/topics/{topicId}/vote"`
    * Enviar o voto para uma sessão de pauta ja aberta `"POST /api/v1/topics/{topicId}/vote"`
    * Exemplo de body:
        ```json
        {
          "cpf": "91273161068", //cpf aleatorio
          "vote": true
        }
        ```

* **Etapa 5:**
    * É enviada a mensagem do voto para o kafka, e o consumer recebe essa mensagem e a persiste no banco.

* **Etapa 6:**
    * Api mobile de retorno de campos com totalizadores no label `"GET api/v1/ui/topics/{topicId}/result"`
    * retorno de totais da votação `"GET api/v1/topics/{topicId}/result"`