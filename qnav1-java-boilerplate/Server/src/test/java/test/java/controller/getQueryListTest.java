package test.java.controller;

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
public class getQueryListTest {
	
	String topicId="1";
	String queryId="11";
	String CommentId="43";
	
	private static final Logger logger = Logger.getLogger(getQueryListTest.class);
	
	@Autowired
	MockMvc mvc;

	@Test
	public void getTopicsTest() throws Exception {
		
		MvcResult result = mvc.perform(get("/getTopics")).andExpect(status().isOk()).andReturn();
		
		String topics = result.getResponse().getContentAsString();
		
		logger.debug( "List of Topics: " +topics);
	}
	

	@Test
	public void getQueriesTest() throws Exception {
		
		MvcResult result = mvc.perform(post("/queryList").contentType(MediaType.APPLICATION_JSON).content(topicId).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
		.andReturn();
		String queryList = result.getResponse().getContentAsString();
		logger.debug( "List of queries under topics: " +queryList);
	}

	@Test
	public void getCommentTest() throws Exception {
		
		MvcResult result = mvc.perform(post("/commentList").contentType(MediaType.APPLICATION_JSON).content("queryId").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
			       
			       	       
		String commentList = result.getResponse().getContentAsString();
		
		logger.debug( "List of comments under query: " +commentList);
	}
	
	@Test
	public void deleteQueryTest() throws Exception {
		
		MvcResult result = mvc.perform(post("/deleteQuery").contentType(MediaType.APPLICATION_JSON).content("queryId").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
			       
			       	       
		String deleteQueryResponse = result.getResponse().getContentAsString();
		
		logger.debug( "Deleted query by ID: " +deleteQueryResponse);
	}
	
	@Test
	public void deleteCommentTest() throws Exception {
		
		MvcResult result = mvc.perform(post("/deleteComment").contentType(MediaType.APPLICATION_JSON).content("CommentId").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
			       
			       	       
		String deleteCommentResponse = result.getResponse().getContentAsString();
		
		logger.debug( "Deleted query by ID: " +deleteCommentResponse);
	}
	
	@Test
	public void postQueryTest() throws Exception {
		
		MvcResult result = mvc.perform(post("/postQuery").
				contentType(MediaType.APPLICATION_JSON).
				content("{\"topicId\": \"1\", \"queries\": \"What is the top end model in Eon\"}")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
			       
			       	       
		String postQuery = result.getResponse().getContentAsString();
		
		logger.debug( "Posted query : " +postQuery);
	}
	
	@Test
	public void postCommentTest() throws Exception {
		
		MvcResult result = mvc.perform(post("/postComment").
				contentType(MediaType.APPLICATION_JSON).
				content("{\"queryId\": \"52\", \"comment\": \"Its Eon Sportz\"}")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
			       
			       	       
		String postComment = result.getResponse().getContentAsString();
		
		logger.debug( "Posted comment : " +postComment);
	}
} 
 
