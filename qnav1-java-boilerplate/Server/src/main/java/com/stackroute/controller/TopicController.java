package com.stackroute.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.NoResultException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.entity.Topic;
import com.stackroute.service.TopicService;

@RestController
public class TopicController {
	@Autowired
	TopicService topicService;

	private static final Logger logger = Logger.getLogger(TopicController.class);

	
	@PostConstruct
	public void Initialize(){
	topicService.topicInitialize();
	}
	
	
	
	@CrossOrigin
	@RequestMapping(value = "/getTopics", method = RequestMethod.GET)
	public @ResponseBody List<Topic> getTopics() {


		List<Topic> topic = new ArrayList<>();

		topic= topicService.viewTopics();

		if (topic.isEmpty()) {
			logger.debug("No Topics found: ");
			throw new NoResultException();
		}
		return topic;
	}
}
