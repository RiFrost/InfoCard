package com.webtech.databaseConnection;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Table(name = "topic")
public class Topic {

    @Id
    @Column(name="topic_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    
    private Long user_id;

    @Column(name="des")
    private String topicname;


	public void setId(Long id) {
		this.id = id;
	}

	public Long getUser_id() {
		return this.user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}


	public String getTopicname() {
		return this.topicname;
	}

	public void setTopicname(String topicname) {
		this.topicname = topicname;
	}

	@Override
	public String toString(){
	return "Topic [id = "+id+", userid = "+user_id+", topic description = "+topicname+"]";
}
    
}
