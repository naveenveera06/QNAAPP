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
public class CommentControllerTest {

	private static final Logger logger = Logger.getLogger(CommentControllerTest.class);
	@Autowired
	MockMvc mvc;
	
	String CommentId="3";
	
	@Test
	public void getCommentTest() throws Exception {
		
		MvcResult result = mvc.perform(get("/commentList/1")).andExpect(status().isOk()).andReturn();
			       
			       	       
		String commentList = result.getResponse().getContentAsString();
		
		logger.debug( "List of comments under query: " +commentList);
	}
	
	@Test
	public void deleteCommentTest() throws Exception {
		
		MvcResult result = mvc.perform(post("/deleteComment").contentType(MediaType.APPLICATION_JSON).content("CommentId").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
			       
			       	       
		String deleteCommentResponse = result.getResponse().getContentAsString();
		
		logger.debug( "Deleted query by ID: " +deleteCommentResponse);
	}
	
	@Test
	public void postCommentTest() throws Exception {
		
		MvcResult result = mvc.perform(post("/postComment/3")
				.content("It will be announced in 2019 start"))
				.andExpect(status().isOk()).andReturn();
			       
			       	       
		String postComment = result.getResponse().getContentAsString();
		
		logger.debug( "Posted comment : " +postComment);
	}
}
