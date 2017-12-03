package com.geog.dao;

import java.util.List;

import com.geog.model.State;
import com.mongodb.MongoException;

public interface HeadOfStateDao {	
	// State section
	public void initMongoConnection() throws Exception;
	public List<State> getAllStates() throws Exception;	
	public void addState(State state) throws Exception;	
	public void deleteState(State state) throws Exception;
}
