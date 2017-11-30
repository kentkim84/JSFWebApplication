package com.geog.controller;

import com.geog.dao.*;
import com.geog.model.*;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
//import javax.faces.bean.RequestScoped;

@ManagedBean
@SessionScoped
//@RequestScoped
public class StateCtrl {
	private GeographyDao geographyDao = new GeographyDaoImpl();
	private List<State> stateList;
	private List<Country> countryList;
	private State state = new State();
	private String returnMessage = new String();
	
	public StateCtrl() {
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
		stateList = new ArrayList<State>(geographyDao.getAllStates());
		return stateList;
	}
	
	public String addState(State state) {
		countryList = geographyDao.getAllCountries();
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
			geographyDao.addState(state);
			return "list_heads_of_state.xhtml";			
		}
		else {
			System.out.println("Cannot add ");
			return null;
		}
	}
	
	public String deleteState(State state) {
		returnMessage = geographyDao.deleteState(state);
		if(returnMessage.contains("constraint")) {			
			return null;
		}
		return "list_heads_of_state.xhtml";
	}

}
