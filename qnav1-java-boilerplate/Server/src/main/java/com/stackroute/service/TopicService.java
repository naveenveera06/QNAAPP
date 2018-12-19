package com.stackroute.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.stackroute.entity.Topic;

@Service
public interface TopicService {
	
	public List<Topic> viewTopics();
	
	public void topicInitialize();

	public Optional<Topic> findTopicId(String id);

		
}
