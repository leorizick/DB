package com.db.desafio_votacao.services.interfaces;

import com.db.desafio_votacao.dto.in.topic.TopicRequestDTO;
import com.db.desafio_votacao.dto.out.topic.TopicResponseDTO;

import java.util.List;

public interface TopicService {
    TopicResponseDTO createTopic(TopicRequestDTO request);
    TopicResponseDTO findTopicById(String id);
    List<TopicResponseDTO> findAll();
}
