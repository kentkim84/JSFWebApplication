package com.geog.dao;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.geog.model.State;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class HeadOfStateImpl implements HeadOfStateDao{
	private MongoClient mongoClient;
	private MongoCollection<Document> headsOfState;
	private List<State> stateList;
		
	public HeadOfStateImpl() throws Exception {
		initMongoConnection();
	}
	
	public void initMongoConnection() throws Exception{
		// establish a connection
		mongoClient = new MongoClient();		
		MongoDatabase database = mongoClient.getDatabase("headsOfStateDB");
		headsOfState =  database.getCollection("headsOfState");		
	}
	
	@Override
	public List<State> getAllStates() throws Exception{		
		stateList = new ArrayList<State>();		
		// Getting the iterable object
		FindIterable<Document> iter = headsOfState.find();
		for (Document doc : iter) {	
			System.out.println(doc.getString("_id")+"\n"+doc.getString("headOfState"));
			// iterate the document object to add values into the state array list			
			State state = new State();
			String _id = doc.getString("_id");
			String headOfState = doc.getString("headOfState");
			state.set_id(_id);
			state.setHeadOfState(headOfState);			
			stateList.add(state);
		}			
		return stateList;
	}
	public void addState(State state) throws Exception{		
		if (state != null) {
			Document doc = new Document()
				.append("_id", state.get_id())
				.append("headOfState", state.getHeadOfState());	            
			headsOfState.insertOne(doc);
			System.out.println("Head of State records added");
	    }	
	}
	public void deleteState(State state) throws Exception{		
		if (state != null) {
			Document doc = new Document("_id", state.get_id());          
			headsOfState.deleteOne(doc);
			System.out.println("Head of State records deleted");
		}	
	}
}
