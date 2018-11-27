package com.stackroute.serviceImpl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.stackroute.entity.Queries;
import com.stackroute.repo.QueryRepo;
import com.stackroute.service.QueryService;

@Component
public class QueryServiceImpl implements QueryService {

	@Autowired
	QueryRepo queryRepo;

	private static final Logger logger = Logger.getLogger(QueryServiceImpl.class);

	/*
	 * Description: Method is used to fetch all the list of queries under the
	 * topic Input: Topic ID
	 */
	public List<Queries> listQuery(Integer id) {
		List<Queries> queriesList = null;
		try {
			queriesList = queryRepo.fetchById(id);
			
			if (!queriesList.isEmpty() && queriesList != null) {
				for (Queries que : queriesList) {

					LocalDate date = que.getRowCreatDt();
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
					String text = date.format(formatter);
					que.setRowCreatDtQuery(text);

				}
				logger.debug("Query Fetch successful" + queriesList);
			} else {
				queriesList = new ArrayList<Queries>();
			}

		} catch (Exception e) {
			logger.error("Error occured in fetching queries by ID: " + id + " " + e);
		}
		return queriesList;

	}

	/*
	 * Description: Method is used to delete the query and it's relevant comment
	 * Input: Query ID
	 */

	public Integer deleteQuery(Integer id) {
		Integer queryDelRes = 0;
		try {

			queryDelRes = queryRepo.deleteQueryById(id);
			if (queryDelRes.equals(1)) {
				logger.debug("Query deleted successful for query ID: " + id);

			}
		} catch (Exception e) {

			logger.error("Error in deleting the query for ID: " + id + " " + e);
		}

		return queryDelRes;

	}

	/*
	 * Description: Method is used to post new query Input: Topic ID, Query
	 */

	public Queries postQuery(Queries queries) {

		try {
			queries.setRowCreatDt(LocalDate.now());
			System.out.println("print queries" + queries);

			if (queries.getQueries() != null && !queries.getQueries().trim().equals("") && queries.getTopicId() != 0
					&& queries.getRowCreatDt() != null) {
				queryRepo.save(queries);

				LocalDate date = queries.getRowCreatDt();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
				String text = date.format(formatter);
				queries.setRowCreatDtQuery(text);
			} else {
				queries = new Queries();
				logger.debug("Any of the input value is null during post query : " + queries);
			}

		} catch (Exception e) {

			logger.error("Error in posting new query: " + queries + " " + e);
		}
		return queries;
	}
}
