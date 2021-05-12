package com.webtech;

import java.util.HashSet;

import com.webtech.database.model.EnumRole;
import com.webtech.database.model.Role;
import com.webtech.database.repository.IndexCardRepository;
import com.webtech.database.repository.RoleRepository;
import com.webtech.database.repository.TopicRepository;
import com.webtech.database.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class MyRunner implements CommandLineRunner {


    @Autowired
    private UserRepository userRepo;

    @Autowired
    private TopicRepository topicRepo;

    @Autowired
    private IndexCardRepository indexCardRepo;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
//        HashSet<Role> roles = new HashSet<Role>();
//        User user1 = new User("Maria", "Ha", "test@mail.de", "123456");
//        user1.setRoles(roles);
//        User user2 = new User("Richard", "Fr", "test@googlemail.de", "123456");
//        user2.setRoles(roles);
//        Topic topic1 = new Topic(user1, "Allgemeinwissen");
//        Topic topic2 = new Topic(user2, "Prog2");
//        Topic topic3 = new Topic(user2, "Statistik");
//        IndexCard indexCard1 = new IndexCard(topic1, "Frage", "Antwort");
//        IndexCard indexCard2 = new IndexCard(topic2, "Frage1", "Antwort1");
//        roleRepository.deleteById((long) 2);
        //roles.add(new Role(EnumRole.ROLE_USER));
//        roleRepository.saveAll(roles);
//        userRepo.saveAll(List.of(user1, user2));
//        topicRepo.saveAll(List.of(topic1, topic2, topic3));
//        indexCardRepo.saveAll(List.of(indexCard1, indexCard2));
    }

}
