package com.db.desafio_votacao.controllers.topic;

import com.db.desafio_votacao.dto.in.topic.TopicRequestDTO;
import com.db.desafio_votacao.dto.out.topic.TopicResponseDTO;
import com.db.desafio_votacao.services.topic.TopicService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/topics")
public class TopicController {

    private final TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @PostMapping
    public ResponseEntity<TopicResponseDTO> createTopic(@RequestBody TopicRequestDTO request) {
        TopicResponseDTO response = topicService.createTopic(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicResponseDTO> findById(@PathVariable String id) {
        TopicResponseDTO response = topicService.findTopicById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<TopicResponseDTO>> findAll() {
        List<TopicResponseDTO> responseList = topicService.findAll();
        return ResponseEntity.ok(responseList);
    }
}
