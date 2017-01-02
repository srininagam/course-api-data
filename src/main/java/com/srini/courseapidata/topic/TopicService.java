package com.srini.courseapidata.topic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicService {

	@Autowired
	private TopicRepository topicRepository;
	
//	private List<Topic> topicList = new ArrayList<>(Arrays.asList(
//										new Topic(1,"Spring", "Spring Framework"),
//										new Topic(2,"Struts", "Struts Framework"),
//										new Topic(3,"Java", "Java Framework")
//									));
	
	public List<Topic> getAllTopics(){
		//return this.topicList;
		List<Topic> mTopicList = topicRepository.getAllTopics();
		
		return mTopicList;
	}
	
	public Topic getTopicById(int id){

		return null;
	}
	
	public Topic addTopic(Topic topic){
		//topicList.add(topic);
		return topicRepository.addTopic(topic);
	}
	
	public int updateTopic( int id, Topic topic){

		return topicRepository.updateTopic(id,topic);
	}
	
	public int removeTopicById(int id){

		return topicRepository.deleteTopic(id);
	}
}
