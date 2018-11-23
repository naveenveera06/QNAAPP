package com.stackroute.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.stackroute.entity.Topic;
import com.stackroute.repo.TopicRepo;
import com.stackroute.service.TopicService;

@Component
public class TopicServiceImpl implements TopicService {

	@Autowired
	TopicRepo topicRepo;
	
	public List<Topic> viewTopics() {
		return topicRepo.findAll();
	}

}
