package com.db.desafio_votacao.dto.out.topic;

import java.time.LocalDateTime;

public class TopicResponseDTO {
    private String id;
    private String tittle;
    private String description;
    private LocalDateTime createdAt;

    public TopicResponseDTO() {
    }

    public TopicResponseDTO(String id, String tittle, String description, LocalDateTime createdAt) {
        this.id = id;
        this.tittle = tittle;
        this.description = description;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getTittle() {
        return tittle;
    }
    public void setTittle(String tittle) {
        this.tittle = tittle;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
