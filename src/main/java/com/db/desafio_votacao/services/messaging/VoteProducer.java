package com.db.desafio_votacao.services.messaging;

import com.db.desafio_votacao.config.KafkaConfig;
import com.db.desafio_votacao.dto.in.kafka.VoteEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class VoteProducer {
    private final KafkaTemplate<String, VoteEvent> kafkaTemplate;

    public VoteProducer(KafkaTemplate<String, VoteEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendVote(VoteEvent event) {
        kafkaTemplate.send(KafkaConfig.VOTE_TOPIC, event.getTopicId(), event);
    }
}
