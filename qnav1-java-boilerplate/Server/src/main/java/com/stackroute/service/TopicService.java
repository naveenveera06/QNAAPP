package com.stackroute.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.stackroute.entity.Topic;

@Service
public interface TopicService {
	
	public List<Topic> viewTopics();

}
