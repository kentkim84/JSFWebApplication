package com.geog.model;

import javax.faces.bean.ManagedBean;  
import javax.faces.bean.RequestScoped; 

@ManagedBean
@RequestScoped
public class State {
	private String _id;
	private String headOfState;
	
	public State() {
		super();
	}
	
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
