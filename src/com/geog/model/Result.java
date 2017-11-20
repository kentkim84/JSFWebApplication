package com.geog.model;


import javax.faces.bean.ManagedBean;  
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class Result extends City{
	private String cty_code;
	private String cty_name;
	private int population;
	private boolean isCoastal;
	private double areaKm;
	// inheritance of City
	private String co_code;
	private String reg_code;
	private String co_name;
	private String reg_name;
	private Country country;
	private Region region;
	
	public Result() {
		super();
		country = new Country();
		region = new Region();
	}
	public String getCty_code() {
		cty_code = super.getCty_code();
		return cty_code;
	}
	
	public void setCty_code(String cty_code) {
		super.setCty_code(cty_code);
	}
	
	public String getCty_name() {
		cty_name = super.getCty_name();
		return cty_name;
	}
	
	public void setCty_name(String cty_name) {
		super.setCty_name(cty_name);
	}
	
	public int getPopulation() {
		population = super.getPopulation();
		return population;
	}
	
	public void setPopulation(int population) {
		super.setPopulation(population);
	}
	
	public boolean getIsCoastal() {
		isCoastal = super.getIsCoastal();
		return isCoastal;
	}
	public void setIsCoastal(boolean isCoastal) {
		super.setIsCoastal(isCoastal);
	}
	
	public double getAreaKm() {
		areaKm = super.getAreaKm();
		return areaKm;
	}
	
	public void setAreaKm(double areaKm) {
		super.setAreaKm(areaKm);
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
	
	public String getCo_name() {
		co_name = country.getCo_name();
		return co_name;
	}
	
	public void setCo_name(String co_name) {
		country.setCo_name(co_name);
	}
	
	public String getReg_name() {
		reg_name = region.getReg_name();
		return reg_name;
	}
	
	public void setReg_name(String reg_name) {
		region.setReg_name(reg_name);
	}

}
