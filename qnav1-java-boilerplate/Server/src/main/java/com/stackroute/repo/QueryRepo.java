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

import com.stackroute.entity.Queries;

/**
 * @author 426141
 *
 */
@Repository("queryRepo")
public interface QueryRepo extends JpaRepository<Queries, Integer> {

	@Query("from Queries q where q.topicId.topicId =:id")
	public List<Queries> fetchById(@Param(value = "id") Integer id);

	@Transactional
	@Modifying
	@Query(value = "delete from Queries where queryId=:id", nativeQuery = false)
	public Integer deleteQueryById(@Param(value = "id") Integer id);

}
