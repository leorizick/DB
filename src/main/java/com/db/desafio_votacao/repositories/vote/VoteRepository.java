package com.db.desafio_votacao.repositories.vote;

import com.db.desafio_votacao.entities.vote.Vote;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VoteRepository extends MongoRepository<Vote, String> {
    boolean existsByTopicIdAndCpf(String topicId, String cpf);
    long countByTopicId(String topicId);
    long countByTopicIdAndVote(String topicId, Boolean vote);
}
