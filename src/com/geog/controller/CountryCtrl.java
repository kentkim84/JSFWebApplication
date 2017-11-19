package com.geog.controller;

import com.geog.dao.*;
import com.geog.model.*;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
//import javax.faces.bean.SessionScoped;
import javax.faces.bean.RequestScoped;

@ManagedBean
//@SessionScoped
@RequestScoped
public class CountryCtrl {
	private GeographyDao geographyDao = new GeographyDaoImpl();
	private List<Country> countryList;
	private Country country = new Country();

	public CountryCtrl() {
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
		countryList = new ArrayList<Country>(geographyDao.getAllCountries());
		return countryList;
	}
	
	public String addCountry(Country country) {
		this.country = country;
		geographyDao.addCountry(this.country);
		return "list_countries.xhtml";
	}
	
	public String deleteCountry(Country country) {
		this.country = country;
		geographyDao.deleteCountry(country);
		return "list_countries.xhtml";
	}
		
	public String updateCountry() {		
//		this.country = country;
		geographyDao.updateCountry(country);
		return "list_countries.xhtml";
	}
	
	public String passValues(Country country) {
		this.country.setCo_code(country.getCo_code());
		this.country.setCo_name(country.getCo_name());
		this.country.setCo_details(country.getCo_details());
		return "update_country.xhtml";
	}
}