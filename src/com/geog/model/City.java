package com.geog.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.faces.bean.ManagedBean;  
import javax.faces.bean.RequestScoped; 

@ManagedBean
@RequestScoped
public class City {
	private String cty_code;
	private String cty_name;
	private int population;
	private double areaKm;
	// country code and region code as foreign keys
	Country co = new Country();
	Region reg = new Region();
	private String co_code = co.getCo_code(); 
	private String reg_code = reg.getReg_code();
	
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
	public double getAreaKm() {
		return areaKm;
	}
	public void setAreaKm(double areaKm) {
		this.areaKm = areaKm;
	}
}
