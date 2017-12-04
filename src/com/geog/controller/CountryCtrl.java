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
public class CountryCtrl {
	private GeographyDao geographyDao;
	private List<Country> countryList;
	private Country country;	
	private String srcCo_code;
	private FacesMessage message;

	public CountryCtrl() {
	}
	
	public void onload() {
		try {
			country = new Country();
			geographyDao = new GeographyDaoImpl();									
			countryList = new ArrayList<Country>(geographyDao.getAllCountries());			
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
		return country.getCo_code();
	}
	
	public void setCo_code(String co_code) {
		country.setCo_code(co_code);
	}
	
	public String getCo_name() {
		return country.getCo_name();
	}
	
	public void setCo_name(String co_name) {
		country.setCo_name(co_name);
	}
	
	public String getCo_details() {
		return country.getCo_details();
	}
	
	public void setCo_details(String co_details) {
		country.setCo_details(co_details);
	}
	
	public List<Country> getCountryList() {
		return countryList;
	}
	
	public String addCountry(Country country) {
		try {
			geographyDao.addCountry(country);
			return "list_countries.xhtml";
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
	
	public String deleteCountry(Country country) {
		try {
			geographyDao.deleteCountry(country);
			return "list_countries.xhtml";
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
		
	public String updateCountry() {
		try {
			geographyDao.updateCountry(country, srcCo_code);
			return "list_countries.xhtml";
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
	
	public String passValues(Country country) {
		this.country.setCo_code(country.getCo_code());
		this.country.setCo_name(country.getCo_name());
		this.country.setCo_details(country.getCo_details());
		srcCo_code = country.getCo_code();		
		return "update_country.xhtml";
	}
}