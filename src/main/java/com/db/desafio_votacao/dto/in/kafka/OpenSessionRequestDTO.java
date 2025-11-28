package com.db.desafio_votacao.dto.in.kafka;

public class OpenSessionRequestDTO {

    private long duration;

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }
}
