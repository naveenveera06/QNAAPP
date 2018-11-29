package com.stackroute.controllertest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.apache.log4j.Logger;
import com.stackroute.qna.QnAApp;


@RunWith(SpringRunner.class)
@SpringBootTest(classes=QnAApp.class)
@AutoConfigureMockMvc
public class QueryControllerTest {

	private static final Logger logger = Logger.getLogger(QueryControllerTest.class);
	@Autowired
	MockMvc mvc;
	
	String topicId="5";
	String queryId="11";
	
	@Test
	public void getQueriesTest() throws Exception {
		
		MvcResult result = mvc.perform(post("/queryList").contentType(MediaType.APPLICATION_JSON).content(topicId).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
		.andReturn();
		String queryList = result.getResponse().getContentAsString();
		logger.debug( "List of queries under topics: " +queryList);
	}
	
	
	@Test
	public void deleteQueryTest() throws Exception {
		
		MvcResult result = mvc.perform(post("/deleteQuery").contentType(MediaType.APPLICATION_JSON).content("queryId").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
			       
			       	       
		String deleteQueryResponse = result.getResponse().getContentAsString();
		
		logger.debug( "Deleted query by ID: " +deleteQueryResponse);
	}
	
	@Test
	public void postQueryTest() throws Exception {
		
		MvcResult result = mvc.perform(put("/postQuery").
				contentType(MediaType.APPLICATION_JSON).
				content("{\"topicId\": \"0\", \"queries\": \"hi\"}")
				//content("{\"topicId\": \"1\", \"queries\": \"\"}")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
			       
			       	       
		String postQuery = result.getResponse().getContentAsString();
		
		logger.debug( "Posted query : " +postQuery);
	}
}
