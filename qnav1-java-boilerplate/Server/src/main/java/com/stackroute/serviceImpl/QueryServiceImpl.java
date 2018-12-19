package com.stackroute.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.stackroute.entity.Queries;
import com.stackroute.repo.QueryRepo;
import com.stackroute.service.QueryService;

@Component
public class QueryServiceImpl implements QueryService {

	@Autowired
	QueryRepo queryRepo;

	
	/*
	 * Description: Method is used to fetch all the list of queries under the
	 * topic Input: Topic ID
	 */
	public List<Queries> listQuery(Integer id) {
				
		return queryRepo.fetchById(id);
	}

	/*
	 * Description: Method is used to delete the query and it's relevant comment
	 * Input: Query ID
	 */

	public Integer deleteQuery(Integer id) {
		

		return queryRepo.deleteQueryById(id);
			
	}

	/*
	 * Description: Method is used to post new query 
	 * Input: Topic ID, Query
	 */

	public Queries postQuery(Queries que) {

				return queryRepo.save(que);
		}

	
	public Optional<Queries> findQueryId(String id) {
		return queryRepo.findById(Integer.parseInt(id));
	}
}
