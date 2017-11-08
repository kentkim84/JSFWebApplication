package com.geog.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.faces.bean.ManagedBean;  
import javax.faces.bean.RequestScoped; 

@ManagedBean
@RequestScoped
public class Region {
	private String reg_code;
	private String reg_name;
	private String reg_details;
	// country code as a foreign key
	Country co = new Country();
	private String co_code = co.getCo_code(); 

	public String getReg_code() {
		return reg_code;
	}
	public void setReg_code(String reg_code) {
		this.reg_code = reg_code;
	}
	public String getReg_name() {
		return reg_name;
	}
	public void setReg_name(String reg_name) {
		this.reg_name = reg_name;
	}
	public String getReg_details() {
		return reg_details;
	}
	public void setReg_details(String reg_details) {
		this.reg_details = reg_details;
	}
	
}
