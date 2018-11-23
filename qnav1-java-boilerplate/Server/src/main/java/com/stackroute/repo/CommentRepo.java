/**
 * 
 */
package com.stackroute.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.stackroute.entity.Comment;

/**
 * @author 426141
 *
 */
@Repository("commentRepo")
public interface CommentRepo extends JpaRepository<Comment, Long> {
	@Query("from Comment where queryId=:id")
    public List<Comment> fetchById(@Param(value = "id") Integer id);

	@Transactional
	@Modifying	
	@Query(value="delete from Comment where commentId=:id", nativeQuery = false)
	public Integer deleteCommentById(@Param(value = "id") Integer id);
}
