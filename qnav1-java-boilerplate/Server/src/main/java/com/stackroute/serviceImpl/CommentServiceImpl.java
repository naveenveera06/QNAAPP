package com.stackroute.serviceImpl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.stackroute.entity.Comment;
import com.stackroute.repo.CommentRepo;
import com.stackroute.service.CommentService;

@Component
public class CommentServiceImpl implements CommentService {

	@Autowired
	CommentRepo commentRepo;

	
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
	
			comment.setRowCreatDt(LocalDate.now());
			
				return commentRepo.save(comment);

	}

}
