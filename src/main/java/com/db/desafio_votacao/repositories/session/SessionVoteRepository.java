package com.db.desafio_votacao.repositories.session;

import com.db.desafio_votacao.entities.session.SessionVote;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface SessionVoteRepository  extends MongoRepository<SessionVote,String> {
    Optional<SessionVote> findByTopicId(String id);
    void deleteByTopicId(String topicId);
}
