package com.geog.model;

import javax.faces.bean.ManagedBean;  
import javax.faces.bean.RequestScoped; 

@ManagedBean
@RequestScoped
public class Region extends Country{
	private String reg_code;
	private String reg_name;
	private String reg_details;
	// inheritance of country code
	private String co_code;

	public Region() {
		super();
	}
	
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
	
	// inheritance of country code
	public String getCo_code() {
		co_code = super.getCo_code(); 
		return co_code;
	}
	
	public void setCo_code(String co_code) {
		super.setCo_code(co_code);
	}
}
