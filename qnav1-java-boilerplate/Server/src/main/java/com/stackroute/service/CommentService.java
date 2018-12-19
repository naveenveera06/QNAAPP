package com.stackroute.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.stackroute.entity.Comment;

@Service
public interface CommentService {

	public List<Comment> listComment(Integer id);

	public Integer deleteComment(Integer id);

	public Comment postComment(Comment cmt);

}
