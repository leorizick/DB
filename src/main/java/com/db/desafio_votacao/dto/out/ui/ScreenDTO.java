package com.db.desafio_votacao.dto.out.ui;

import java.util.List;

public class ScreenDTO {
    private String type;
    private String title;
    private String description;

    private List<FormFieldDTO> items;
    private List<ActionButtonDTO> actions;

    private List<SelectionItemDTO> options;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<FormFieldDTO> getItems() {
        return items;
    }

    public void setItems(List<FormFieldDTO> items) {
        this.items = items;
    }

    public List<ActionButtonDTO> getActions() {
        return actions;
    }

    public void setActions(List<ActionButtonDTO> actions) {
        this.actions = actions;
    }

    public List<SelectionItemDTO> getOptions() {
        return options;
    }

    public void setOptions(List<SelectionItemDTO> options) {
        this.options = options;
    }
}
