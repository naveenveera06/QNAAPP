package com.stackroute.controller;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.validation.Valid;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.entity.Queries;
import com.stackroute.service.QueryService;
import com.stackroute.serviceImpl.QueryServiceImpl;

@RestController
public class QueryController {
	@Autowired
	QueryService queryService;

	private static final Logger logger = Logger.getLogger(QueryServiceImpl.class);

	/*
	 * Description: Method is used to fetch all the list of queries under the
	 * topic Input: Topic ID
	 */

	@CrossOrigin("*")
	@RequestMapping(value = "/queryList", method = RequestMethod.POST)

	public @ResponseBody List<Queries> getQueryList(  @RequestBody String Id) {

		String number = Id;
		Integer id = Integer.parseInt(number);
		List<Queries> queries = new ArrayList<>();
		//
			queries = queryService.listQuery(id);

			if (queries.isEmpty()) {
				logger.debug("No query found for ID: " + +id);
				throw new NoResultException();
			}
		/*} catch (Exception e) {
			logger.error("Unknown error while fetching querylist for topic ID: " + +id);
			e.printStackTrace();
			throw new UnknownError();
		}*/
		return queries;
	}

	/*
	 * Description: Method is used to delete the query and it's relevant comment
	 * Input: Query ID
	 */

	@CrossOrigin("*")
	@RequestMapping(value = "/deleteQuery", method = RequestMethod.DELETE)
	public @ResponseBody Integer getdeleteQueryById( @RequestBody String Id) {

		String number = Id;
		Integer id = Integer.parseInt(number);
		Integer deleteQueryStatus = 0;
		try {
			deleteQueryStatus = queryService.deleteQuery(id);
			if (queryService.equals(1)) {
				logger.debug("Query deleted successful for query ID: " + id);
			}
		} catch (Exception e) {

			logger.error("Error in deleting the query for ID: " + id + " " + e);
			throw new UnknownError();
		}
		return deleteQueryStatus;
	}

	/*
	 * Description: Method is used to post new query 
	 * Input: Topic ID, Query
	 */
	@CrossOrigin("*")
	@RequestMapping(value = "/postQuery", method = RequestMethod.PUT)
	public @ResponseBody Queries postNewQuery(@Valid @RequestBody Queries queries) {
		Queries que = null;
		try {
			if (queries.getQueries() == null || queries.getQueries().trim().equals("") || queries.getTopicId() == 0) {
				throw new IllegalArgumentException();
			}

			que = queryService.postQuery(queries);
		} catch (Exception e) {

			logger.error("Error in posting query " + e);
			throw new UnknownError();
		}
		return que;
	}
}
