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
public class CityCtrl {
	private GeographyDao geographyDao = new GeographyDaoImpl();
	private List<City> cityList;
	private List<Result> resultList;
	private City city = new City();
	private String condition;
	private String returnMessage = new String();
	
	public CityCtrl() {
	}

	public String getCo_code() {
		return city.getCo_code();
	}
	
	public void setCo_code(String co_code) {
		city.setCo_code(co_code);
	}
	
	public String getReg_code() {
		return city.getReg_code();
	}
	
	public void setReg_code(String reg_code) {
		city.setReg_code(reg_code);
	}
	
	public String getCty_code() {
		return city.getCty_code();
	}
	
	public void setCty_code(String cty_code) {
		city.setCty_code(cty_code);
	}
	
	public String getCty_name() {
		return city.getCty_name();
	}
	
	public void setCty_name(String cty_name) {
		city.setCty_name(cty_name);
	}
	
	public int getPopulation() {
		return city.getPopulation();
	}
	
	public void setPopulation(int population) {
		city.setPopulation(population);
	}
	
	public boolean getIsCoastal() {
		return city.getIsCoastal();
	}
	public void setIsCoastal(boolean isCoastal) {
		city.setIsCoastal(isCoastal);
	}
	
	public double getAreaKm() {
		return city.getAreaKm();
	}
	
	public void setAreaKm(double areaKm) {
		city.setAreaKm(areaKm);
	}
	
	public String getCo_name() {
		return geographyDao.getValueFromMultiTables(this.city, "co_name", "co_code", "country");
	}
	
	public String getReg_name() {
		return geographyDao.getValueFromMultiTables(this.city, "reg_name", "reg_code", "region");
	}
	
	public String getReturnMessage() {
		return returnMessage;
	}
	
	public void setReturnMessage(String errorMessage) {
		this.returnMessage = errorMessage;
	}
	
	public List<City> getCityList() {
		cityList = new ArrayList<City>(geographyDao.getAllCities());
		return cityList;
	}
		
	public String addCity(City city) {
		geographyDao.addCity(city);
		return "list_cities.xhtml";
	}
	
	public String displayCity(City city) {
		this.city.setCty_code(city.getCty_code());
		this.city.setCty_name(city.getCty_name());
		// country and region name are executed from separated methods
		this.city.setPopulation(city.getPopulation());
		this.city.setIsCoastal(city.getIsCoastal());
		this.city.setAreaKm(city.getAreaKm());
		System.out.println(this.city.getCty_code());
		return "display_city.xhtml";
	}
	
	public String getCondition() {
		return condition;
	}
	
	public void setCondition(String condition) {
		switch (condition) {
		case "lt":
			this.condition = "<";
			break;
		case "gt":
			this.condition = ">";
			break;
		case "lte":
			this.condition = "<=";
			break;
		case "gte":
			this.condition = ">=";
			break;
		case "eq":
			this.condition = "=";
			break;
		default:			
			break;
		}		
	}
	
	public String searchCities(City city) {		
		resultList = new ArrayList<Result>(geographyDao.searchCities(city, condition));		
		return "find_result.xhtml";
	}
	
	public List<Result> getResultList() {
		return resultList;
	}
	
	// release constraint
	public String deleteCity() {				
		System.out.println(this.city.getCty_code());
		System.out.println(city.getCty_code());
		geographyDao.deleteCity(city);
		return "list_cities.xhtml";
	}
	
	// not required methods
	public String updateCity() {		
		geographyDao.updateCity(city);
		return "list_cities.xhtml";
	}
	
	public String passValues(City city) {
		this.city.setCty_code(city.getCty_code());
		this.city.setCo_code(city.getCo_code());
		this.city.setReg_code(city.getReg_code());
		this.city.setCty_name(city.getCty_name());
		this.city.setPopulation(city.getPopulation());
		this.city.setIsCoastal(city.getIsCoastal());
		this.city.setAreaKm(city.getAreaKm());
		return "update_city.xhtml";
	}


}
