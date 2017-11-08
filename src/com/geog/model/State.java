package com.geog.model;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import javax.faces.bean.ManagedBean;  
import javax.faces.bean.RequestScoped; 

@ManagedBean
@RequestScoped
public class State {
	private String _id;
	private String headOfState;
	
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getHeadOfState() {
		return headOfState;
	}
	public void setHeadOfState(String headOfState) {
		this.headOfState = headOfState;
	}
	
	
}
