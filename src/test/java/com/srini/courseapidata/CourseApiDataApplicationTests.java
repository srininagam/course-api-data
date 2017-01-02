package com.srini.courseapidata;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.srini.courseapidata.topic.Topic;
import com.srini.courseapidata.topic.TopicService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CourseApiDataApplication.class)
@WebAppConfiguration
public class CourseApiDataApplicationTests {

	@Test
	public void contextLoads() {
	}
	
	@Autowired
	private TopicService service;
	
	@Before
	public void setUp(){
		//
	}
	
	@Test
	public void getAllTopics() {
		List<Topic> list = service.getAllTopics();
		
		Assert.assertNotNull("list is null", list);
		Assert.assertEquals("Failure - expected size is 1", 1, list.size());
	}

}
