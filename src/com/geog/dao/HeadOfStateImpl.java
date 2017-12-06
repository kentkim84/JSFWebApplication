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
		// connection error will throw the exception
		if (mongoClient != null) {
			System.out.println("Open the Mongo Connection");
		}
	}
	
	public void closeMongoConnection() throws Exception{
		// close the connection
		mongoClient.close();
		if (mongoClient != null) {
			System.out.println("Close the Mongo Connection");
		}
	}
	
	// Get List:
	// 1. iterate over results of collection.find()
	// 2. add values into the state array list
	// 3. close the connection
	@Override
	public List<State> getAllStates() throws Exception{		
		stateList = new ArrayList<State>();		
		// Getting the iterable object
		FindIterable<Document> iter = headsOfState.find();
		
		for (Document doc : iter) {	
			System.out.println(doc.toJson());
			State state = new State();
			String _id = doc.getString("_id");
			String headOfState = doc.getString("headOfState");
			state.set_id(_id);
			state.setHeadOfState(headOfState);			
			stateList.add(state);
		}
		closeMongoConnection();
		return stateList;
	}
	
	// Add values:
	// 1. prepare the new document object with values
	// user wishes to add into the db
	// 2. call 'collection.insertone()' and put the prepared document
	public void addState(State state) throws Exception{		
		if (state != null) {
			Document doc = new Document()
				.append("_id", state.get_id())
				.append("headOfState", state.getHeadOfState());	            
			headsOfState.insertOne(doc);
			System.out.println("Head of State records added");
			closeMongoConnection();
	    }	
	}
	
	// Delete values:
	// 1. prepare the new document object with the value 
	// which indicates where user wishes to delete from
	// 2. call 'collection.deleteone()' and put the prepared document
	public void deleteState(State state) throws Exception{		
		if (state != null) {
			Document doc = new Document("_id", state.get_id());          
			headsOfState.deleteOne(doc);
			System.out.println("Head of State records deleted");
			closeMongoConnection();
		}	
	}
}
