package com.db.desafio_votacao.services.result;

import com.db.desafio_votacao.dto.out.vote.VoteResultDTO;
import com.db.desafio_votacao.entities.topics.Topic;
import com.db.desafio_votacao.repositories.topics.TopicRepository;
import com.db.desafio_votacao.repositories.vote.VoteRepository;
import org.springframework.stereotype.Service;

@Service
public class VoteResultServiceImpl implements VoteResultService{

    private final VoteRepository voteRepository;
    private final TopicRepository topicRepository;

    public VoteResultServiceImpl(VoteRepository voteRepository,
                                 TopicRepository topicRepository) {
        this.voteRepository = voteRepository;
        this.topicRepository = topicRepository;
    }

    @Override
    public VoteResultDTO calculateResult(String topicId) {
        Topic topic = topicRepository.findById(topicId)
                .orElseThrow(() -> new RuntimeException("Topic not found: " + topicId));

        long total = voteRepository.countByTopicId(topicId);
        long yes = voteRepository.countByTopicIdAndVote(topicId, true);
        long no = voteRepository.countByTopicIdAndVote(topicId, false);

        return new VoteResultDTO(
                topic.getId(),
                topic.getTitle(),
                total,
                yes,
                no
        );
    }
}
