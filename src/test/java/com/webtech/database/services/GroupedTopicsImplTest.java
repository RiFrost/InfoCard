package com.webtech.database.services;

import com.webtech.database.repository.GroupedTopics;

public class GroupedTopicsImplTest implements GroupedTopics {

  private Long id;
  private String topicName;
  private int indexCardCount;


  public GroupedTopicsImplTest(Long id, String topicName, int indexCardCount) {
    this.id = id;
    this.topicName = topicName;
    this.indexCardCount = indexCardCount;
  }

  @Override
  public int getIndexCardCount() {
    // TODO Auto-generated method stub
    return this.indexCardCount;
  }

  @Override
  public Long getTopic_id() {
    // TODO Auto-generated method stub
    return this.id;
  }

  @Override
  public String getDes() {
    // TODO Auto-generated method stub
    return this.topicName;
  }
  
}
