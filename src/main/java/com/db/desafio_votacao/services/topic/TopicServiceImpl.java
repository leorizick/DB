package com.db.desafio_votacao.services.topic;

import com.db.desafio_votacao.dto.in.topic.TopicRequestDTO;
import com.db.desafio_votacao.dto.out.topic.TopicResponseDTO;
import com.db.desafio_votacao.entities.topics.Topic;
import com.db.desafio_votacao.repositories.topics.TopicRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicServiceImpl implements TopicService {
    private final TopicRepository repository;

    public TopicServiceImpl(TopicRepository repository) {
        this.repository = repository;
    }

    @Override
    public TopicResponseDTO createTopic(TopicRequestDTO request) {
        Topic topic = new Topic(request.getTitle(), request.getDescription());
        Topic save = repository.save(topic);
        return toResponse(topic);
    }

    @Override
    public TopicResponseDTO findTopicById(String id) {
        Topic topic = repository.findById(id).orElseThrow(() -> new RuntimeException("Topic not found " + id));
        return toResponse(topic);
    }

    @Override
    public List<TopicResponseDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(topic -> toResponse(topic))
                .toList();
    }

    private TopicResponseDTO toResponse(Topic topic) {
        return new TopicResponseDTO(
                topic.getId(),
                topic.getTitle(),
                topic.getDescription(),
                topic.getCreatedAt()
        );
    }
}
