package com.db.desafio_votacao.repositories.topics;

import com.db.desafio_votacao.entities.topics.Topic;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends MongoRepository<Topic, String> {
}
