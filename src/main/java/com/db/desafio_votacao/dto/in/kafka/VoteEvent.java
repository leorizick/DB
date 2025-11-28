package com.db.desafio_votacao.dto.in.kafka;

public class VoteEvent {

    private String topicId;
    private String sessionId;
    private String cpf;
    private boolean vote;

    public VoteEvent(){

    }

    public VoteEvent(String topicId, String sessionId, String cpf, boolean vote) {
        this.topicId = topicId;
        this.sessionId = sessionId;
        this.cpf = cpf;
        this.vote = vote;
    }

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public boolean isVote() {
        return vote;
    }

    public void setVote(boolean vote) {
        this.vote = vote;
    }
}
