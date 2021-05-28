package com.webtech.infocard;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class TopicResponse {

    @NotNull(message = "TopicId is null!")
    private long id;

    @NotNull(message = "TopicName is null!")
    @NotBlank(message = "Themenbezeichnung darf nicht leer sein!")
    private String topicName;

    private int indexCardCount;

    public TopicResponse(long topicId, String topicName, int indexCardCount) {
        this.id = topicId;
        this.topicName = topicName;
        this.indexCardCount = indexCardCount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public int getIndexCardCount() {
        return this.indexCardCount;
    }

    public void setIndexCardCount(int indexCardCount) {
        this.indexCardCount = indexCardCount;
    }


}
