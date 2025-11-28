package com.db.desafio_votacao.controllers.result;

import com.db.desafio_votacao.dto.out.vote.VoteResultDTO;
import com.db.desafio_votacao.services.result.VoteResultService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/topics/{topicId}/result")
public class VoteResultController {
    private final VoteResultService voteResultService;

    public VoteResultController(VoteResultService voteResultService) {
        this.voteResultService = voteResultService;
    }

    @GetMapping
    public ResponseEntity<VoteResultDTO> getResult(@PathVariable String topicId) {
        VoteResultDTO result = voteResultService.calculateResult(topicId);
        return ResponseEntity.ok(result);
    }
}
