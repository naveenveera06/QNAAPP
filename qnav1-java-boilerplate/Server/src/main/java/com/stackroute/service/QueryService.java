package com.stackroute.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.stackroute.entity.Queries;

@Service
public interface QueryService {
	
	public List<Queries> listQuery(Integer id) ;

	public Integer deleteQuery(Integer id);

	public Queries postQuery(Queries que) ;

	public Optional<Queries> findQueryId(String id);
		
} 
