package com.webtech.infocard.responsemodel;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class IndexCardResponse {

    @NotNull(message = "IndexCardId is null!")
    private Long id;

    @NotNull(message = "Question is null!")
    @NotBlank(message = "Question must not be empty!")
    private String question;

    @NotNull(message = "Answer is null!")
    @NotBlank(message = "Answer must not be empty!")
    private String answer;

    private Boolean isFavored;

    public IndexCardResponse() {};

    public IndexCardResponse(Long topicId, String question, String answer, Boolean isFavored) {
        this.id = topicId;
        this.question = question;
        this.answer = answer;
        this.isFavored = isFavored;
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

    public Boolean isFavored() {
        return this.isFavored;
    }


    public void setIsFavored(Boolean isFavored) {
        this.isFavored = isFavored;
    }

}

