package com.db.desafio_votacao.services.vote;

import com.db.desafio_votacao.dto.in.kafka.VoteEvent;
import com.db.desafio_votacao.dto.in.vote.VoteRequestDTO;
import com.db.desafio_votacao.entities.session.SessionVote;
import com.db.desafio_votacao.services.external.CpfValidationClient;
import com.db.desafio_votacao.services.messaging.VoteProducer;
import com.db.desafio_votacao.services.session.SessionVoteService;
import org.springframework.stereotype.Service;

@Service
public class VoteServiceImpl implements VoteService {

    private final SessionVoteService sessionVoteService;
    private final VoteProducer voteProducer;
    private final CpfValidationClient cpfValidationClient;

    public VoteServiceImpl(SessionVoteService sessionVoteService, VoteProducer voteProducer, CpfValidationClient cpfValidationClient) {
        this.sessionVoteService = sessionVoteService;
        this.voteProducer = voteProducer;
        this.cpfValidationClient = cpfValidationClient;
    }

    @Override
    public void vote(String topicId, VoteRequestDTO request) {
        SessionVote sessionVote = sessionVoteService.findByTopicId(topicId);

        if (!sessionVote.isOpen()) {
            throw new RuntimeException("This topic session is already closed");
        }

        //CLIENT FAKE PARA VALIDAÇÃO DE CPF
        boolean canVote = cpfValidationClient.validateCpf(request.getCpf());

        if (!canVote) {
            throw new RuntimeException("Invalid CPF");
        }

        VoteEvent event = new VoteEvent(
                topicId,
                sessionVote.getId(),
                request.getCpf(),
                request.getVote()
        );

        voteProducer.sendVote(event);
    }
}
