package com.geog.model;


import javax.faces.bean.ManagedBean;  
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class City extends Region{
	private String cty_code;
	private String cty_name;
	private int population;
	private boolean isCoastal;
	private double areaKm;
	// inheritance of country code and region code
	private String co_code; 
	private String reg_code;
	
	public City() {
		super();
	}
	public String getCty_code() {
		return cty_code;
	}
	
	public void setCty_code(String cty_code) {
		this.cty_code = cty_code;
	}
	
	public String getCty_name() {
		return cty_name;
	}
	
	public void setCty_name(String cty_name) {
		this.cty_name = cty_name;
	}
	
	public int getPopulation() {
		return population;
	}
	
	public void setPopulation(int population) {
		this.population = population;
	}
	
	public boolean getIsCoastal() {
		return isCoastal;
	}
	public void setIsCoastal(boolean isCoastal) {
		this.isCoastal = isCoastal;
	}
	
	public double getAreaKm() {
		return areaKm;
	}
	
	public void setAreaKm(double areaKm) {
		this.areaKm = areaKm;
	}
	
	// inheritance of country code
	public String getCo_code() {
		co_code = super.getCo_code();
		return co_code;
	}
	
	public void setCo_code(String co_code) {
		super.setCo_code(co_code);
	}
	
	public String getReg_code() {
		reg_code = super.getReg_code();
		return reg_code;
	}
	
	public void setReg_code(String reg_code) {
		super.setReg_code(reg_code);
	}

}
