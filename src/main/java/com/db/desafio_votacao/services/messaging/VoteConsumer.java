package com.db.desafio_votacao.services.messaging;

import com.db.desafio_votacao.config.KafkaConfig;
import com.db.desafio_votacao.dto.in.kafka.VoteEvent;
import com.db.desafio_votacao.entities.vote.Vote;
import com.db.desafio_votacao.repositories.vote.VoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class VoteConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(VoteConsumer.class);

    private final VoteRepository voteRepository;

    public VoteConsumer(VoteRepository voteRepository){
        this.voteRepository = voteRepository;
    }

    @KafkaListener(topics = KafkaConfig.VOTE_TOPIC, groupId = "vote-group", containerFactory = "voteKafkaListenerContainerFactory")
    public void consumerVote(VoteEvent event) {
        LOGGER.info("Consuming vote of cpf {} in topic {}", event.getCpf(), event.getTopicId());

        if (voteRepository.existsByTopicIdAndCpf(event.getTopicId(), event.getCpf())) {
            LOGGER.warn("Cpf {} already vote in this topic {}", event.getCpf(), event.getTopicId());
            return;
        }

        Vote vote = new Vote(
                event.getTopicId(),
                event.getSessionId(),
                event.getCpf(),
                event.isVote()
        );

        voteRepository.save(vote);
        LOGGER.info("Vote persisted in topic {}", event.getTopicId());
    }
}
