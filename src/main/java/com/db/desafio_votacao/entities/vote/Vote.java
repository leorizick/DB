package com.db.desafio_votacao.entities.vote;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "votes")
@CompoundIndex(name = "topic_cpf_idx", def = "{'topicId': 1, 'cpf': 1}", unique = true)
public class Vote {

    @Id
    private String Id;

    private String topicId;
    private String sessionId;
    private String cpf;
    private boolean vote;
    private LocalDateTime createdAt;

    public Vote(){

    }

    public Vote(String topicId, String sessionId, String cpf, boolean vote) {
        this.topicId = topicId;
        this.sessionId = sessionId;
        this.cpf = cpf;
        this.vote = vote;
        this.createdAt = LocalDateTime.now();
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
