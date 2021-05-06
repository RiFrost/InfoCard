package com.webtech.database.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "indexcard")
public class IndexCard {

    @Id
    @Column(name = "card_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "topic_id", nullable = false)
    private Topic topic;

    @Column(nullable = false)
    @NotBlank(message = "question can't be blank")
    private String question;

    @Lob
    @Column(nullable = false)
    @NotBlank(message = "answer can't be blank")
    private String answer;

    public IndexCard() {
    }

    public IndexCard(Topic topic, String question, String answer) {
        this.topic = topic;
        this.question = question;
        this.answer = answer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
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

    @Override
    public String toString() {
        return "IndexCard{" +
                "id=" + id +
                ", topic=" + topic +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }
}
