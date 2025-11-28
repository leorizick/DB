package com.db.desafio_votacao.services.session;

import com.db.desafio_votacao.entities.session.SessionVote;
import com.db.desafio_votacao.entities.topics.Topic;
import com.db.desafio_votacao.repositories.session.SessionVoteRepository;
import com.db.desafio_votacao.repositories.topics.TopicRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SessionVoteServiceImpl implements SessionVoteService {

    private final SessionVoteRepository sessionVoteRepository;
    private final TopicRepository topicRepository;

    public SessionVoteServiceImpl(SessionVoteRepository sessionVoteRepository, TopicRepository topicRepository) {
        this.sessionVoteRepository = sessionVoteRepository;
        this.topicRepository = topicRepository;
    }

    @Override
    public SessionVote openSession(String topicId, Long duration) {
        Topic topic = topicRepository.findById(topicId)
                .orElseThrow(() -> new RuntimeException("Topic not found: " + topicId));

        long durationOrDefault = (duration != null && duration > 0) ? duration : 1L;

        LocalDateTime opening = LocalDateTime.now();
        LocalDateTime closening = opening.plusMinutes(durationOrDefault);

        sessionVoteRepository.deleteByTopicId(topicId);
        SessionVote sessionVote = new SessionVote(topic.getId(), opening, closening);
        return sessionVoteRepository.save(sessionVote);
    }

    @Override
    public SessionVote findByTopicId(String topicId) {
        return sessionVoteRepository.findByTopicId(topicId)
                .orElseThrow(() -> new RuntimeException("Session not found for this topic: " + topicId));
    }
}
