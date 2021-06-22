package com.webtech.infocard.responsemodel;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class IndexCardResponse {

    @NotNull(message = "IndexCardId is null!")
    private Long id;

    @NotNull(message = "Question is null!")
    @NotBlank(message = "Fragenfeld darf nicht leer sein!")
    private String question;

    @NotNull(message = "Answer is null!")
    @NotBlank(message = "Antwortfeld darf nicht leer sein!")
    private String answer;

    public IndexCardResponse() {};

    public IndexCardResponse(Long topicId, String question, String answer) {
        id = topicId;
        this.question = question;
        this.answer = answer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}

