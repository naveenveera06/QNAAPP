package com.stackroute.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.stackroute.entity.Queries;

@Service
public interface QueryService {
	
	public List<Queries> listQuery(Integer id);

	public Integer deleteQuery(Integer id);

	public Queries postQuery(Queries queries) throws Exception;
		
} 
