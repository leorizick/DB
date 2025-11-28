package com.db.desafio_votacao.services.session;

import com.db.desafio_votacao.entities.session.SessionVote;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

public interface SessionVoteService {

    SessionVote openSession(String topicId, Long duration);
    SessionVote findByTopicId(String topicId);
}
