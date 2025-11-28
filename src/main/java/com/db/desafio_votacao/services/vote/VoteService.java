package com.db.desafio_votacao.services.vote;

import com.db.desafio_votacao.dto.in.vote.VoteRequestDTO;

public interface VoteService {
    void vote(String topicId, VoteRequestDTO request);
}
