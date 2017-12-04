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
public class CityCtrl {
	private GeographyDao geographyDao;
	private List<City> cityList;
	private List<Result> resultList;
	private City city;
	private String condition;
	private FacesMessage message;
	
	public CityCtrl() {
	}
	
	public void onload() {
		try {
			city = new City();
			geographyDao = new GeographyDaoImpl();			
			cityList = new ArrayList<City>(geographyDao.getAllCities());			
		} catch (Exception e) {
			e.printStackTrace();
			// connection error handling
			if (e.toString().contains("SQLException") 
					&& (e.toString().contains("CommunicationsException")
					|| e.toString().contains("ConnectException")
					|| e.toString().contains("SocketException"))) {
				FacesMessage message = new FacesMessage(e.getMessage());
				FacesContext.getCurrentInstance().addMessage(null, message);
			}
			// other error handling
			// display the exception
			else {
				message = new FacesMessage("Error: " + e.toString());
				FacesContext.getCurrentInstance().addMessage(null, message);
			}
		}		
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
		String co_name;
		try {
			co_name = geographyDao.getValueFromMultiTables(this.city, "co_name", "co_code", "country");
		} catch (Exception e) {
			e.printStackTrace();
			co_name = e.getMessage();
		}
		return co_name;
	}
	
	public String getReg_name() {
		String reg_name;	
		try {
			reg_name = geographyDao.getValueFromMultiTables(this.city, "reg_name", "reg_code", "region");
		} catch (Exception e) {
			e.printStackTrace();
			reg_name = e.getMessage();
		}
		return reg_name;
	}

	public List<City> getCityList() {
		return cityList;
	}
		
	public String addCity(City city) {
		try {
			geographyDao.addCity(city);;
			return "list_cities.xhtml";
		} catch (Exception e) {
			e.printStackTrace();
			// connection error handling
			if (e.toString().contains("CommunicationsException") 
					|| e.toString().contains("SocketException")
					|| e.toString().contains("ConnectException")) {
				message = new FacesMessage(e.getMessage());
				FacesContext.getCurrentInstance().addMessage(null, message);				
			}
			// sql update error handling
			else if (e.toString().contains("MySQLIntegrityConstraintViolationException")) {
				message = new FacesMessage(e.getMessage());
				FacesContext.getCurrentInstance().addMessage(null, message);
			}
			// other error handling
			// display the exception
			else {
				message = new FacesMessage("Error: " + e.toString());
				FacesContext.getCurrentInstance().addMessage(null, message);
			}
			return null;
		}
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
		try {
			resultList = new ArrayList<Result>(geographyDao.searchCities(city, condition));
			return "find_result.xhtml";
		} catch (Exception e) {
			e.printStackTrace();
			// connection error handling
			if (e.toString().contains("CommunicationsException") 
					|| e.toString().contains("SocketException")
					|| e.toString().contains("ConnectException")) {
				message = new FacesMessage(e.getMessage());
				FacesContext.getCurrentInstance().addMessage(null, message);				
			}
			// sql update error handling
			else if (e.toString().contains("MySQLIntegrityConstraintViolationException")) {
				message = new FacesMessage(e.getMessage());
				FacesContext.getCurrentInstance().addMessage(null, message);
			}
			// other error handling
			// display the exception
			else {
				message = new FacesMessage("Error: " + e.toString());
				FacesContext.getCurrentInstance().addMessage(null, message);
			}
			return null;
		}
	}
	
	public List<Result> getResultList() {
		return resultList;
	}
	
	// release constraint
	public String deleteCity() {				
		System.out.println(this.city.getCty_code());
		System.out.println(city.getCty_code());
		try {
			geographyDao.deleteCity(city);
			return "list_cities.xhtml";
		} catch (Exception e) {
			e.printStackTrace();
			// connection error handling
			if (e.toString().contains("CommunicationsException") 
					|| e.toString().contains("SocketException")
					|| e.toString().contains("ConnectException")) {
				message = new FacesMessage(e.getMessage());
				FacesContext.getCurrentInstance().addMessage(null, message);				
			}
			// sql update error handling
			else if (e.toString().contains("MySQLIntegrityConstraintViolationException")) {
				message = new FacesMessage(e.getMessage());
				FacesContext.getCurrentInstance().addMessage(null, message);
			}
			// other error handling
			// display the exception
			else {
				message = new FacesMessage("Error: " + e.toString());
				FacesContext.getCurrentInstance().addMessage(null, message);
			}
			return null;
		}
	}
	
	// not required methods
	public String updateCity() {		
		try {
			geographyDao.updateCity(city);
			return "list_cities.xhtml";
		} catch (Exception e) {
			e.printStackTrace();
			// connection error handling
			if (e.toString().contains("CommunicationsException") 
					|| e.toString().contains("SocketException")
					|| e.toString().contains("ConnectException")) {
				message = new FacesMessage(e.getMessage());
				FacesContext.getCurrentInstance().addMessage(null, message);				
			}
			// sql update error handling
			else if (e.toString().contains("MySQLIntegrityConstraintViolationException")) {
				message = new FacesMessage(e.getMessage());
				FacesContext.getCurrentInstance().addMessage(null, message);
			}
			// other error handling
			// display the exception
			else {
				message = new FacesMessage("Error: " + e.toString());
				FacesContext.getCurrentInstance().addMessage(null, message);
			}
			return null;
		}
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
