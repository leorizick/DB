package com.db.desafio_votacao.controllers.session;

import com.db.desafio_votacao.dto.in.kafka.OpenSessionRequestDTO;
import com.db.desafio_votacao.entities.session.SessionVote;
import com.db.desafio_votacao.services.session.SessionVoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/topics/{topicId}/sessions")
public class SessionVoteController {

    private final SessionVoteService sessionVoteService;

    public SessionVoteController(SessionVoteService sessionVoteService) {
        this.sessionVoteService = sessionVoteService;
    }

    @PostMapping
    public ResponseEntity<SessionVote> openSession(@PathVariable String topicId,
                                                   @RequestBody(required = false) OpenSessionRequestDTO request) {

        Long duration = (request != null) ? request.getDuration() : null;

        SessionVote session = sessionVoteService.openSession(topicId, duration);
        return ResponseEntity.status(HttpStatus.CREATED).body(session);
    }
}
