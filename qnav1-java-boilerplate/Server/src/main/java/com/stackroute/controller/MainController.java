/**
 * 
 */
package com.stackroute.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.stackroute.entity.Comment;
import com.stackroute.entity.Queries;
import com.stackroute.entity.Topic;
import com.stackroute.service.CommentService;
import com.stackroute.service.QueryService;
import com.stackroute.service.TopicService;

/**
 * @author 426141
 *
 */
@RestController
public class MainController {

	@Autowired
	TopicService topicService;

	@Autowired
	QueryService queryService;

	@Autowired
	CommentService commentService;

	@CrossOrigin
	@RequestMapping(value = "/getTopics", method = RequestMethod.GET)
	public @ResponseBody List<Topic> getTopics() {

		return topicService.viewTopics();
	}

	@CrossOrigin("*")
	@RequestMapping(value = "/queryList", method = RequestMethod.POST)

	public @ResponseBody List<Queries> getQueryList(@RequestBody  String Id) {

		String number=Id;
		Integer id=Integer.parseInt(number);

		return queryService.listQuery(id);

	} 

	@CrossOrigin("*")
	@RequestMapping(value = "/commentList", method = RequestMethod.POST)
	public @ResponseBody List<Comment> getCommentList(@RequestBody (required = false) String Id) {

		String number=Id;
		Integer id=Integer.parseInt(number);
		return commentService.listComment(id);
	}

	@CrossOrigin("*")
	@RequestMapping(value = "/deleteQuery", method = RequestMethod.POST)
	public @ResponseBody Integer  getdeleteQueryById(@RequestBody String Id) {

		String number=Id;
		Integer id=Integer.parseInt(number);

		return queryService.deleteQuery(id);
	}

	@CrossOrigin("*")
	@RequestMapping(value = "/deleteComment", method = RequestMethod.POST)
	public @ResponseBody Integer  getdeleteCommentById(@RequestBody String Id) {

		String number=Id;
		Integer id=Integer.parseInt(number);

		return commentService.deleteComment(id);
	}

	@CrossOrigin("*")
	@RequestMapping(value = "/postQuery", method = RequestMethod.POST)
	public @ResponseBody Queries  postNewQuery(@RequestBody  Queries queries) throws Exception {
		return queryService.postQuery(queries);
	}


	@CrossOrigin("*")
	@RequestMapping(value = "/postComment", method = RequestMethod.POST)
	public @ResponseBody Comment  postNewComment(@RequestBody  Comment comment) {
		return commentService.postComment(comment); 
	}
}
