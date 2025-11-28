package com.db.desafio_votacao.services.result;

import com.db.desafio_votacao.dto.out.vote.VoteResultDTO;

public interface VoteResultService {
    VoteResultDTO calculateResult(String topicId);
}
