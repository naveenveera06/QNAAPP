package com.stackroute.serviceImpl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
		List<Comment> commentList = null;
		try {
			commentList = commentRepo.fetchById(id);

			if (!commentList.isEmpty() && commentList != null) {
				for (Comment cmt : commentList) {
					LocalDate date = cmt.getRowCreatDt();
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
					String text = date.format(formatter);

					cmt.setRowCreatDtComment(text);
				}

				logger.debug("Query Fetch successful" + commentList);
			}

		} catch (Exception e) {

			logger.error("Error occured in fetching queries by ID: " + id + " " + e);
		}
		return commentList;

	}

	
	/*
	 * Description: Method is used to delete the comments under the specific query 
	   Input: Comment ID
	 */
	public Integer deleteComment(Integer id) {
		Integer result = 0;
		try {

			result = commentRepo.deleteCommentById(id);
			if (result.equals(1)) {
				logger.debug("Comment deleted successful for comment ID: " + id);

			}
		} catch (Exception e) {

			logger.error("Error in deleting the comment for ID: " + id + " " + e);
		}
		return result;
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

				LocalDate date = comment.getRowCreatDt();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
				String text = date.format(formatter);

				comment.setRowCreatDtComment(text);
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
