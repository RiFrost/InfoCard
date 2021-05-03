package com.webtech.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "topic")
public class Topic {

    @Id
    @Column(name = "topic_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<IndexCard> indexCards = new HashSet<>();

    @ManyToOne()
    @JoinColumn(name = "customer_id", nullable = false)
    private User user;


    @Column(name = "des", nullable = false, length = 100)
    private String topicName;

    public Topic() {
    }

    public Topic(User user, String topicName) {
        this.user = user;
        this.topicName = topicName;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public Set<IndexCard> getIndexCards() {
        return indexCards;
    }

    @Override
    public String toString() {
        return "TopicId=" + id + ", topicName='" + topicName;
    }
}
