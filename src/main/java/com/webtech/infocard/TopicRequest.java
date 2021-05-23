package com.webtech.infocard;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class TopicRequest {
    @NotNull(message = "TopicId is null!")
    private long id;

    @NotNull(message = "TopicName is null!")
    @NotBlank(message = "Themenbezeichnung darf nicht leer sein!")
    private String topicName;

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
}
