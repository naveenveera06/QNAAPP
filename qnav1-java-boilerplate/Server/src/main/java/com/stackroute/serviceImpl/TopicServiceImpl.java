package com.stackroute.serviceImpl;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

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
	
	public Optional<Topic> findTopicId(String id){
		
	return topicRepo.findById(Integer.parseInt(id));
	}
	
	
	@Override
	public void topicInitialize() {
		// TODO Auto-generated method stub
		List<Topic> topics = topicRepo.findAll();
		if(topics.isEmpty()) {
			Topic topic = new Topic();
			topic.setTopic("HYUNDAI");
			topic.setDescription("NEW THINKING, NEW POSSIBILITIES");
			topicRepo.save(topic);
			topic = new Topic();
			topic.setTopic("RENAULT");
			topic.setDescription("PASSION FOR LIFE");
			topicRepo.save(topic);
			topic = new Topic();
			topic.setTopic("SUZUKI");
			topic.setDescription("WAY OF LIFE");
			topicRepo.save(topic);
			topic = new Topic();
			topic.setTopic("HONDA");
			topic.setDescription("ESCAPE FROM THE ORDINARY");
			topicRepo.save(topic);
			topic = new Topic();
			topic.setTopic("NISSAN");
			topic.setDescription("INNOVATION THAT EXCITES");
			topicRepo.save(topic);
		}
	}

}
