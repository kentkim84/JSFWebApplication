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
	
	public StateCtrl() {
	}
	
	public void onload() {
		try {			
			state = new State();
			headOfStateDao = new HeadOfStateImpl();
			geographyDao = new GeographyDaoImpl();
			stateList = new ArrayList<State>(headOfStateDao.getAllStates());
			countryList = new ArrayList<Country>(geographyDao.getAllCountries());		
		} catch (Exception e) { 
			e.printStackTrace();
			if (e.getMessage().contains("Connection")) {
				FacesMessage message = new FacesMessage("Error: Mongo Database Connection Failed");
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
	
	public String addState(State state) {
		/*try {
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		boolean _idFound = false;
		for (Country country : countryList) {
			System.out.println("iteration: " + country.getCo_code() + " " + country.getCo_name());
			if (state.get_id().equalsIgnoreCase(country.getCo_code())) {
				// handle the error, cannot add this state records
				_idFound = true;
				System.out.println("found: " + state.get_id());
			}
		}		
		if(_idFound == true) {			
			System.out.println("Cannot add " + state.get_id());
			try {
				headOfStateDao.addState(state);
			} catch (Exception e) {				
				e.printStackTrace();
			}
			return "list_heads_of_state.xhtml";			
		}
		else {
			System.out.println("Cannot add ");
			return null;
		}
	}
	
	public String deleteState(State state) {
		try {
			headOfStateDao.deleteState(state);
			return "list_heads_of_state.xhtml";
		} catch (Exception e) {
			return null;
		}
	}

}
