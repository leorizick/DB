package com.db.desafio_votacao.dto.in.vote;

public class VoteRequestDTO {

    private String cpf;
    private Boolean vote;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Boolean getVote() {
        return vote;
    }

    public void setVote(Boolean vote) {
        this.vote = vote;
    }
}
