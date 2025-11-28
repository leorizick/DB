package com.db.desafio_votacao.entities.session;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "session_vote")
public class SessionVote {

    @Id
    private String id;

    private String topicId;
    private LocalDateTime opening;
    private LocalDateTime closening;

    public boolean isOpen() {
        LocalDateTime now = LocalDateTime.now();
        return (now.isAfter(opening) || now.isEqual(opening)) && now.isBefore(closening);
    }

    public  SessionVote(){

    }

    public SessionVote(String topicId, LocalDateTime opening, LocalDateTime closening) {
        this.topicId = topicId;
        this.opening = opening;
        this.closening = closening;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public LocalDateTime getOpening() {
        return opening;
    }

    public void setOpening(LocalDateTime opening) {
        this.opening = opening;
    }

    public LocalDateTime getClosening() {
        return closening;
    }

    public void setClosening(LocalDateTime closening) {
        this.closening = closening;
    }
}
