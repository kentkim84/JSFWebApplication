package com.geog.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.faces.bean.ManagedBean;  
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class Country {
	private String co_code;
	private String co_name;
	private String co_details;
	
	public Country() {
		super();		
	}

	public Country(String co_code, String co_name, String co_details) {
		super();
		this.co_code = co_code;
		this.co_name = co_name;
		this.co_details = co_details;
	}

	public String getCo_code() {
		return co_code;
	}

	public void setCo_code(String co_code) {
		this.co_code = co_code;
	}

	public String getCo_name() {
		return co_name;
	}

	public void setCo_name(String co_name) {
		this.co_name = co_name;
	}
	
	public String getCo_details() {
		return co_details;
	}

	public void setCo_details(String co_details) {
		this.co_details = co_details;
	}
	
}
