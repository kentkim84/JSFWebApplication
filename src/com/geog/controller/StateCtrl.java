package com.geog.controller;

import com.geog.dao.*;
import com.geog.model.*;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class StateCtrl {
	private HeadOfStateDao headOfStateDao;
	private GeographyDao geographyDao;
	private List<State> stateList;
	private List<Country> countryList;
	private State state;
	private FacesMessage message;
	
	public StateCtrl() {
	}
	
	// called before page is rendered
	public void onLoad(String page) {
		try {
			// 1. list page will initiate the connection 
			// 2. pre-load the array list only once before page is rendered
			// 3. if error occurs, system displays error messages
			if (page.equals("list")) {				
				System.out.println("Page is: " + page);
				headOfStateDao = new HeadOfStateImpl();
				state = new State();
				stateList = new ArrayList<State>(headOfStateDao.getAllStates());
			}
			else {
				System.out.println("Page is: " + page);
			}
		} catch (Exception e) { 
			e.printStackTrace();
			// connection error handling
			if (e.toString().contains("MongoException") 
					|| e.toString().contains("MongoSocketOpenException")
					|| e.toString().contains("ConnectException")
					|| e.toString().contains("MongoTimeoutException")) {
				FacesMessage message = new FacesMessage("Error: Cannot connect to Mongo Database");
				FacesContext.getCurrentInstance().addMessage(null, message);
			}
			// other error handling
			// display the exception
			else {
				message = new FacesMessage("Error: " + e.toString());
				FacesContext.getCurrentInstance().addMessage(null, message);
			}
		}
	}
	
	
	
	public String get_id() {
		return state.get_id();
	}
	
	public void set_id(String _id) {
		state.set_id(_id);
	}
	
	public String getHeadOfState() {
		return state.getHeadOfState();
	}
	
	public void setHeadOfState(String headOfState) {
		state.setHeadOfState(headOfState);
	}
	
	public List<State> getStateList() {
		return stateList;
	}
	
	// 1. initiate the connection
	// 2. try to find the value exist in the country table
	// 3. if exist, try to add values into database
	// 4. if error occurs, system displays error messages
	public String addState(State state) {
		boolean _idFound = false;			
		try {				
			geographyDao = new GeographyDaoImpl();
			countryList = new ArrayList<Country>(geographyDao.getAllCountries());
			for (Country country : countryList) {
				System.out.println("iteration in the country list: " + country.getCo_code() + " " + country.getCo_name());
				if (state.get_id().equalsIgnoreCase(country.getCo_code())) {
					// handle the error, cannot add this state records
					_idFound = true;
					System.out.println("found: " + state.get_id());
				}
			}
			if(_idFound == true) {
				headOfStateDao = new HeadOfStateImpl();
				headOfStateDao.addState(state);
				return "list_heads_of_state.xhtml";
			}
			else {
				message = new FacesMessage("Error: Cannot Add Head of State '" + state.getHeadOfState() 
					+ "' Country Code '" + state.get_id() + "' does not exists");
				FacesContext.getCurrentInstance().addMessage(null, message);
				System.out.println("Error: Cannot add, could not find " + state.get_id());
				return null;
			}					
		} catch (Exception me) {				
			me.printStackTrace();
			// connection error handling
			if (me.toString().contains("MongoException") 
					|| me.toString().contains("MongoSocketOpenException")
					|| me.toString().contains("ConnectException")
					|| me.toString().contains("MongoTimeoutException")) {
				FacesMessage message = new FacesMessage("Error: Cannot connect to Mongo Database");
				FacesContext.getCurrentInstance().addMessage(null, message);
			}
			else if (me.toString().contains("SQLException")
					|| me.toString().contains("CommunicationsException") 
					|| me.toString().contains("SocketException")
					|| me.toString().contains("ConnectException")) {
				message = new FacesMessage("Error: Cannot connect to Sql Database");
				FacesContext.getCurrentInstance().addMessage(null, message);				
			}
			else if (me.toString().contains("MongoWriteException")) {
				message = new FacesMessage("Error: Cannot Add Head of State '" + state.getHeadOfState() 
				+ "' Country Code '" + state.get_id() + "' already exists");
				FacesContext.getCurrentInstance().addMessage(null, message);				
			}
			// other error handling
			// display the exception
			else {
				message = new FacesMessage("Error: " + me.toString());
				FacesContext.getCurrentInstance().addMessage(null, message);
			}
			// try to close the remaining connection
			// if still connected, system will close it
			try {
				headOfStateDao.closeMongoConnection();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			return null;
		}		
	}
	
	// 1. initiate the connection
	// 2. try to delete values from database
	// 3. if error occurs, system displays error messages
	public String deleteState(State state) {
		try {
			headOfStateDao = new HeadOfStateImpl();
			headOfStateDao.deleteState(state);
			return "list_heads_of_state.xhtml";
		} catch (Exception e) {
			e.printStackTrace();
			// connection error handling
			if (e.toString().contains("MongoException") 
					|| e.toString().contains("MongoSocketOpenException")
					|| e.toString().contains("ConnectException")
					|| e.toString().contains("MongoTimeoutException")) {
				FacesMessage message = new FacesMessage("Error: Cannot connect to Mongo Database");
				FacesContext.getCurrentInstance().addMessage(null, message);
			}
			// other error handling
			// display the exception
			else {
				message = new FacesMessage("Error: " + e.toString());
				FacesContext.getCurrentInstance().addMessage(null, message);
			}
			// try to close the remaining connection
			// if still connected, system will close it
			try {
				headOfStateDao.closeMongoConnection();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			return null;
		}
	}
	
}
