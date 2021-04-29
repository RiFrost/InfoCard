package com;




import java.util.List;

import com.webtech.databaseConnection.Topic;
import com.webtech.databaseConnection.TopicRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;




@SpringBootApplication
public class InfocardApplication implements CommandLineRunner{

	@Autowired
	private TopicRepository topicRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(InfocardApplication.class, args);
	}


		@Override
		public void run(String... args) throws Exception {
			List<Topic> topicList = topicRepository.findAll();
			topicList.forEach(System.out::println);
		}

	// @Bean
	// public CommandLineRunner demo (TopicRepository topicRepository)  {
    // return args -> {
	// 	List<Topic> topicList = topicRepository.findAll();
	// 	topicList.forEach(System.out::println);
    //  };
	// }
}


		
		

