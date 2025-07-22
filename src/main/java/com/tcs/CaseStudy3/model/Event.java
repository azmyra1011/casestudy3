package com.tcs.CaseStudy3.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection="events")
public class Event {

    @Id
    private String id;

    private String name;
    private String type;
    private String description;
    private List<CustomQuestion> custom_questions;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<CustomQuestion> getCustom_questions() {
        return custom_questions;
    }

    public void setCustom_questions(List<CustomQuestion> custom_questions) {
        this.custom_questions = custom_questions;
    }

    public static class CustomQuestion {
        private String question;
        private String type;
        private boolean required;

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public boolean isRequired() {
            return required;
        }

        public void setRequired(boolean required) {
            this.required = required;
        }
    }
}
