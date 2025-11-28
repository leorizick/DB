package com.db.desafio_votacao.dto.out.ui;

import java.util.Map;

public class ActionButtonDTO {
    private String label;
    private String url;
    private Map<String, Object> body; // bod

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, Object> getBody() {
        return body;
    }

    public void setBody(Map<String, Object> body) {
        this.body = body;
    }
}
