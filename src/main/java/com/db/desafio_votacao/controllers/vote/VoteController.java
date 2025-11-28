package com.db.desafio_votacao.controllers.vote;

import com.db.desafio_votacao.dto.in.vote.VoteRequestDTO;
import com.db.desafio_votacao.services.vote.VoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/topics/{topicId}/vote")
public class VoteController {

    private final VoteService voteService;

    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @PostMapping
    public ResponseEntity<Void> vote(@PathVariable String topicId, @RequestBody VoteRequestDTO request){
        voteService.vote(topicId, request);

        return ResponseEntity.accepted().build();
    }
}
