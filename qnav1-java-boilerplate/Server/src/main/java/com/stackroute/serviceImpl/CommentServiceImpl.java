package com.stackroute.serviceImpl;

import java.time.LocalDate;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.stackroute.entity.Comment;
import com.stackroute.repo.CommentRepo;
import com.stackroute.service.CommentService;

@Component
public class CommentServiceImpl implements CommentService {

	@Autowired
	CommentRepo commentRepo;

	private static final Logger logger = Logger.getLogger(CommentServiceImpl.class);

	/*
	 * Description: Method is used to fetch all the list of comments under the query
	   Input: Query ID
	 */

	public List<Comment> listComment(Integer id) {

		return commentRepo.fetchById(id);

	}


	/*
	 * Description: Method is used to delete the comments under the specific query 
	   Input: Comment ID
	 */
	public Integer deleteComment(Integer id) {
		return commentRepo.deleteCommentById(id);
	}



	/*
	 * Description: Method is used to post new comment 
	   Input: Query ID, Comment
	 */
	public Comment postComment(Comment comment) {
		try {

			comment.setRowCreatDt(LocalDate.now());
			System.out.println("print queries" + comment);
			if (comment.getComment() != null && !comment.getComment().trim().equals("") && comment.getQueryId() != 0 && comment.getRowCreatDt() != null) {

				commentRepo.save(comment);

			} else {
				comment= new Comment();
				logger.debug("Any of the input value is null during post comment : " + comment);
			}
		} catch (Exception e) {

			logger.error("Error in posting new query: " + comment + " " + e);
		}
		System.out.println("print comment ueries" + comment);
		return comment;
	}

}
