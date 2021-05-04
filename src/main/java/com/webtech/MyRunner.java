package com.webtech;

import com.webtech.model.IndexCard;
import com.webtech.model.Topic;
import com.webtech.model.User;
import com.webtech.repository.IndexCardRepository;
import com.webtech.repository.TopicRepository;
import com.webtech.repository.UserRepository;
import com.webtech.service.TopicService;
import com.webtech.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class MyRunner implements CommandLineRunner {


    @Autowired
    private UserRepository userRepo;

    @Autowired
    private TopicRepository topicRepo;

    @Autowired
    private IndexCardRepository indexCardRepo;

    @Autowired
    private TopicService topicService;

    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {
        User user1 = new User("Maria", "H", "test@mail.de");
        User user2 = new User("Richard", "F", "test@googlemail.de");
        Topic topic1 = new Topic(user1, "Allgemeinwissen");
        Topic topic2 = new Topic(user2, "Prog2");
        Topic topic3 = new Topic(user2, "Statistik");
        IndexCard indexCard1 = new IndexCard(topic1, "Frage", "Antwort");
        IndexCard indexCard2 = new IndexCard(topic2, "Frage1", "Antwort1");
        userRepo.saveAll(List.of(user1, user2));
        topicRepo.saveAll(List.of(topic1, topic2, topic3));
        indexCardRepo.saveAll(List.of(indexCard1, indexCard2));
    }

}
