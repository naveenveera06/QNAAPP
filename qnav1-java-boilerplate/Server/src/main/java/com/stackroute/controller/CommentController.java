package com.stackroute.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.NoResultException;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
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

@RestController
public class CommentController {
	@Autowired
	CommentService commentService;

	@Autowired
	QueryService queryService;
	
	private static final Logger logger = Logger.getLogger(CommentController.class);

	/*
	 * Description: Method is used to fetch all the list of comments under the
	 * query Input: Query ID
	 */

	@CrossOrigin("*")
	@RequestMapping(value = "/commentList/{id}", method = RequestMethod.GET)
	public @ResponseBody List<Comment> getCommentList(  @PathVariable("id") String Id) {

		String number = Id;
		Integer id = Integer.parseInt(number);
		List<Comment> comment = new ArrayList<>();


		comment = commentService.listComment(id);

		if (comment.isEmpty()) {
			logger.debug("No Comment found for ID: " + +id);
			throw new NoResultException();
		}

		return comment;

	}

	/*
	 * Description: Method is used to delete the comments under the specific
	 * query Input: Comment ID
	 */

	@CrossOrigin("*")
	@RequestMapping(value = "/deleteComment", method = RequestMethod.DELETE)
	public @ResponseBody Integer getdeleteCommentById( @RequestBody String Id) {

		String number = Id;
		Integer id = Integer.parseInt(number);

		Integer deleteCommentStatus = 0;
		try {
			deleteCommentStatus = commentService.deleteComment(id);
			if (deleteCommentStatus.equals(1)) {
				logger.debug("Query deleted successful for query ID: " + id);
			}
		} catch (Exception e) {

			logger.error("Error in deleting the query for ID: " + id + " " + e);
			throw new UnknownError();
		}
		return deleteCommentStatus;
	}

	/*
	 * Description: Method is used to post new comment Input: Query ID, Comment
	 */

	@CrossOrigin("*")
	@RequestMapping(value = "/postComment/{id}", method = RequestMethod.POST)
	public @ResponseBody Comment postNewComment(@PathVariable("id") String id, @RequestBody String comment) {

		Comment cmt = new Comment();
		try {
			Optional<Queries> queryIdCheck = queryService.findQueryId(id);
			if (queryIdCheck.isPresent()) {
				cmt.setComment(comment);
				cmt.setQueryId(queryIdCheck.get());
				cmt.setRowCreatDt(LocalDate.now());
				
			cmt = commentService.postComment(cmt);
			}
			else{
				logger.debug("Invalid query ID");
				throw new NoResultException();
			}
		} catch (Exception e) {

			logger.error("Error in posting comment " + e);
			throw new UnknownError();
		}
		return cmt;
	}
}
